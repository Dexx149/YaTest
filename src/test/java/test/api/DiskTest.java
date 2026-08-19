package test.api;

import assertions.ApiAssert;
import org.junit.jupiter.api.Test;
import service.DiskService;

public class DiskTest {

    private final DiskService diskService= new DiskService();

    @Test
    public void shouldReturnDiskInfo() {
        ApiAssert.assertThat(diskService.getInfo()).hasStatus(200);
    }
}
