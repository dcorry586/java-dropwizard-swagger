package org.example.resources;

import io.swagger.annotations.Api;
import org.example.api.OrderService;
import org.example.cli.Order;
import org.example.cli.OrderRequest;
import org.example.cli.OrderRequestUpdate;
import org.example.cli.ProductRequest;
import org.example.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api("Engineering Academy DropWizard Order API")
@Path("/api")
public class OrderController {
    private OrderService orderService = new OrderService();

    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders() throws FailedToGetOrdersException {
        try {
            return Response.ok(orderService.getAllOrders()).build();
        } catch (FailedToGetOrdersException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersById(@PathParam("id") int id) throws FailedToGetOrdersException, OrderDoesNotExistException {
        try {
            return Response.ok(orderService.getOrderById(id)).build();
        } catch (FailedToGetOrdersException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (OrderDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

//    @POST
//    @Path("/orders")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createOrder(OrderRequest order) {
//        try {
//            return Response.ok(orderService.createOrder(order)).build();
//        } catch (FailedToCreateOrderException e) {
//            System.err.println(e.getMessage());
//            return Response.serverError().build();
//        } catch (InvalidOrderException | CustomerDoesNotExistException e) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
//        }
//    }

    @PUT
    @Path("/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrder(@PathParam("id") int id, OrderRequestUpdate order) {
        try {
            orderService.updateOrder(id, order);
            return Response.ok().build();
        } catch (InvalidOrderException | OrderDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (FailedToUpdateOrderException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }

    }
}
