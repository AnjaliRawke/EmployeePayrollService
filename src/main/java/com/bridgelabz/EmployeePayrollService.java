package com.bridgelabz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
	List<EmployeePayroll> list;

	public EmployeePayrollService(List<EmployeePayroll> list) {
		this.list = list;
	}

	public PayrollService getPayrollService(IOService ioType) {
		PayrollService payrollService;
		if (IOService.FILE_IO.equals(ioType)) {
			payrollService = new FileIOImpl();
		} else if (IOService.DATABASE_IO.equals(ioType)) {
			payrollService = new DatabaseIOImpl();
		} else if (IOService.CONSOLE_IO.equals(ioType)) {
			payrollService = new ConsoleIOImpl();
		} else {
			payrollService = new CloudIOImpl();
		}
		return payrollService;
	}

	public void writeData(IOService ioType) {
		// Abstraction
		PayrollService payrollService = getPayrollService(ioType);
		try {
			payrollService.writeData(list);
		} catch (IOException e) {
			System.out.println("catch block");
		}
	}

	public void readData(IOService ioType) {

		PayrollService payrollService = getPayrollService(ioType);
		try {
			payrollService.readData();
		} catch (IOException e) {
			System.out.println("catch block");
		}
	}


	public Long countEntries(IOService ioType) {
		PayrollService payrollService = getPayrollService(ioType);
		try {
			return payrollService.countEntries();
		} catch (IOException e) {
			System.out.println("catch block");
		}
		return null;
	}
}