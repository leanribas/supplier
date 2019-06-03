package br.com.leandro.neomind.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.leandro.neomind.domain.supplier.Supplier;
import br.com.leandro.neomind.exception.SupplierException;
import br.com.leandro.neomind.service.SupplierService;

@Path("supplierlist")
public class SupplierController {

	private SupplierService supplierService = new SupplierService();
	
	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Supplier findById(@PathParam("id") int id) {

		try {			
			return supplierService.findById(id);
		} catch (SupplierException e) {
			buildError(e.getMessage());			
		} catch (Exception e) {
			buildError("Não foi possível recuperar fornecedores.");			
		}
		
		return null;

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Supplier> list() {

		try {			
			return supplierService.listAll();
		} catch (Exception e) {
			buildError("Não foi possível recuperar lista de fornecedores.");
			return null;
		}

	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Supplier createSupplier(Supplier supplier) {
		
		try {			
			supplierService.create(supplier);
			return supplier;
		} catch (SupplierException e) {
			buildError(e.getMessage());
		} catch (Exception e) {
			buildError("Não foi possível criar fornecedor.");
		}

		return null;
	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void updateSupplier(Supplier supplier) {

		try {			
			supplierService.update(supplier);
			//return "Fornecedor alterado.";
		} catch (SupplierException e) {
			buildError(e.getMessage());
		} catch (Exception e) {
			buildError("Não foi possível atualizar fornecedor.");
		}
		
		//return "";

	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void deleteSupplier(Supplier supplier) {

		try {			
			supplierService.delete(supplier.getId());
			//return "Fornecedor removido.";
		} catch (SupplierException e) {
			buildError(e.getMessage());
		} catch (Exception e) {
			buildError("Não foi possível remover fornecedor.");
		}
		
		//return "";

	}
	
	public void buildError(String msg) {

		throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("{\"message\":\"" + msg + "\"}").build());

	}

}
