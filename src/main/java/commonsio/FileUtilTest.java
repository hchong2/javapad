package commonsio;

import java.io.IOException;
import java.math.BigInteger;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;

public class FileUtilTest {
	public static void main(String[] args) throws IOException {
		
		System.out.println(FileUtils.ONE_GB);
		System.out.println(FileUtils.byteCountToDisplaySize(FileUtils.ONE_GB));
		System.out.println(FileUtils.byteCountToDisplaySize(FileUtils.ONE_PB));
		System.out.println(FileUtils.byteCountToDisplaySize(FileUtils.ONE_ZB));
		System.out.println(FileUtils.byteCountToDisplaySize(FileUtils.ONE_EB));
		System.out.println(FileSystemUtils.freeSpaceKb());
		
		System.out.println(FileUtils.byteCountToDisplaySize(FileSystemUtils.freeSpaceKb() * 1024));
		System.out.println(FileUtils.byteCountToDisplaySize((long)401241316 * 1024));
		System.out.println(FileUtils.byteCountToDisplaySize(BigInteger.valueOf(Long.valueOf("500107862016"))));
	}
}
