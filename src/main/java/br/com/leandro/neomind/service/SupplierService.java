package br.com.leandro.neomind.service;

import java.util.List;

import br.com.leandro.neomind.domain.supplier.Supplier;
import br.com.leandro.neomind.domain.supplier.SupplierRepository;
import br.com.leandro.neomind.exception.SupplierException;
import br.com.leandro.neomind.util.Email;
import br.com.leandro.neomind.util.ValidaCNPJ;

public class SupplierService {

	private SupplierRepository repositorySupplier = new SupplierRepository();

	public void validDataSupplier(Supplier supplier) throws SupplierException {

		if (supplier == null) {
			throw new SupplierException("Nenhuma informa��o enviada.");
		}
		
		if (supplier.getCnpj() == null || supplier.getCnpj().isEmpty()) {
			throw new SupplierException("CNPJ n�o informado.");
		}

		if (!ValidaCNPJ.isCNPJ(supplier.getCnpj())) {
			throw new SupplierException("N�mero do CNPJ n�o � v�lido.");
		}
		
		if (supplier.getName() == null || supplier.getName().isEmpty()) {
			throw new SupplierException("Nome n�o informado.");
		}

		if (supplier.getEmail() == null || supplier.getEmail().isEmpty()) {
			throw new SupplierException("Email n�o informado.");
		}
		
		if (!Email.isValid(supplier.getEmail())) {
			throw new SupplierException("Email n�o � v�lido.");
		}

	}

	public void create(Supplier supplier) throws SupplierException {

		validDataSupplier(supplier);
		if (findByCnpj(supplier.getCnpj()) != null) {
			throw new SupplierException("CNPJ j� cadastrado.");
		}
		repositorySupplier.store(supplier);
	}

	public void update(Supplier supplier) throws SupplierException {

		validDataSupplier(supplier);
		Supplier supplierStore = findById(supplier.getId());
		
		if (!supplierStore.getCnpj().equals(supplier.getCnpj())) {
			if (findByCnpj(supplier.getCnpj()) != null) {
				throw new SupplierException("CNPJ j� cadastrado.");
			}
		}		
		repositorySupplier.update(supplier);
	}

	public void delete(int id) throws SupplierException {
		findById(id);
		repositorySupplier.delete(id);
	}

	public List<Supplier> listAll() {

		return repositorySupplier.list();

	}

	public Supplier findByCnpj(String cnpj) {
		return repositorySupplier.findByCnpj(cnpj);
	}

	public Supplier findById(int id) throws SupplierException {

		if (id < 1) {
			throw new SupplierException("ID de Fornecedor inv�lida.");
		}

		Supplier s = repositorySupplier.findById(id);
		
		if (s == null) {
			throw new SupplierException("Fornecedor n�o encontrado.");
		}

		return s;
	}
}
