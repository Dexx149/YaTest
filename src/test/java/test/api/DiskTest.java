package test.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import service.DiskService;

import static org.assertj.core.api.Assertions.assertThat;

public class DiskTest {

    private final DiskService diskService= new DiskService();

    @Test
    public void shouldReturnDiskInfo() {
        assertThat(diskService.getInfo().statusCode()).isEqualTo(200);
    }
}
