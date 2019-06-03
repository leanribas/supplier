package br.com.leandro.neomind.domain.supplier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.leandro.neomind.repository.InterfaceRepository;

public class SupplierRepository implements InterfaceRepository<Supplier> {

	private String fileName = "supplierData";

	private Map<Integer, Supplier> supplierData;

	public SupplierRepository() {
		supplierData = new HashMap<>();
		this.loadData();
	}

	@SuppressWarnings("unchecked")
	public void loadData() {
		
		try (FileInputStream file = new FileInputStream(this.fileName); ObjectInputStream inputObj = new ObjectInputStream(file)) {
			
			supplierData = (Map<Integer, Supplier>) inputObj.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void storeData() {

		try (FileOutputStream file = new FileOutputStream(this.fileName);
				ObjectOutputStream outputObj = new ObjectOutputStream(file)) {

			outputObj.writeObject(supplierData);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Supplier store(Supplier supplier) {
		supplier.setId(supplierData.size() + 1);
		supplierData.put(supplier.getId(), supplier);
		this.storeData();
		return supplier;
	}

	public void update(Supplier supplier) {

		if (supplier.getId() > 0) {

			supplierData.put(supplier.getId(), supplier);
			this.storeData();

		}

	}

	public Supplier findById(int id) {
		return supplierData.get(id);

	}

	public Supplier findByCnpj(String cnpj) {

		List<Supplier> listSupplier = supplierData.values().stream().filter(s -> s.getCnpj().equals(cnpj))
				.collect(Collectors.toList());

		if (listSupplier.size() > 0) {
			return listSupplier.get(0);
		}

		return null;

	}

	public void delete(int id) {

		supplierData.remove(id);
		this.storeData();

	}

	public List<Supplier> list() {
		return supplierData.values().stream().collect(Collectors.toList());
	}
}
