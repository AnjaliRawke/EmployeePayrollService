package com.bridgelabz;

import java.io.IOException;
import java.util.List;

public interface PayrollService {
	void writeData(List<EmployeePayroll> list) throws IOException;

	void readData() throws IOException;

	long countEntries() throws IOException;
}
