package cn.race.feign.clients;

import cn.race.common.response.Result;
import cn.race.feign.config.FeignMultipartConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "excel",configuration = FeignMultipartConfig.class)
@Component
public interface ExcelClient {
    @PostMapping(value = "readExcel",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
     Result simpleRead(@RequestParam("file") MultipartFile file);
}
