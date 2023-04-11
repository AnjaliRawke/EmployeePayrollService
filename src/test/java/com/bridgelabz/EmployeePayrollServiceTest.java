package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class EmployeePayrollServiceTest {
	private static String HOME=System.getProperty("user.home");
	private static String PLAY_WITH_NIO="TempPlayGround";

	@Test
	public void GivenPathWhenCheckedThenConfirm() throws IOException {
		// check file exist
		Path homepath = Paths.get(HOME);
		Assert.assertTrue(Files.exists(homepath));

		// delete file and check file not exist
		Path playPath = Paths.get(HOME + "/"+PLAY_WITH_NIO);
		if(Files.exists(playPath))
			FileOperation.deleteFiles(playPath.toFile());
		Assert.assertTrue(Files.notExists(playPath));

		// Create Directory
		Files.createDirectories(playPath);
		Assert.assertTrue(Files.exists(playPath));
		//  Create file
		IntStream.range(1,10).forEach(cntr->{
			Path tempFile =Paths.get(playPath+"/temp"+cntr);
			Assert.assertTrue(Files.notExists(tempFile));
			try{
				Files.createFile(tempFile);
			}
			catch (IOException e){
				System.out.println("Catch");
			}
			Assert.assertTrue(Files.exists(tempFile));
		});

		//List files, Directories as well as Files with Extension
		Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
		Files.newDirectoryStream(playPath).forEach(System.out::println);
		Files.newDirectoryStream(playPath,path-> path.toFile().isFile()&&path.toString().startsWith("temp")).forEach(System.out::println);
	}

	@Test
	public void givenThreeEmployeesWhenWrittenToFileAndReadShouldReturnCount() {
		EmployeePayroll emp1 = new EmployeePayroll(1, "Anjali", 250000);
		EmployeePayroll emp2 = new EmployeePayroll(2, "Apeksha", 260000);
		EmployeePayroll emp3 = new EmployeePayroll(3, "Vaishnavi", 270000);

		List<EmployeePayroll> list = new ArrayList<>();
		list.add(emp1);
		list.add(emp2);
		list.add(emp3);

		EmployeePayrollService employeePayrollService = new EmployeePayrollService(list);
		employeePayrollService.writeData(IOService.FILE_IO);
		employeePayrollService.readData(IOService.FILE_IO);
		long count = employeePayrollService.countEntries(IOService.FILE_IO);
		Assertions.assertEquals(3, count);
	}
}
