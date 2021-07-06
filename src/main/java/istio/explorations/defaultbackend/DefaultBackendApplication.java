package istio.explorations.defaultbackend;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


@RestController
@SpringBootApplication
public class DefaultBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefaultBackendApplication.class, args);
	}
	@GetMapping(
			value = "/**",
			produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[]  notFound(){
		String imageLoc = System.getenv().getOrDefault("URI_404", "EMPTY_LOCATION");
		System.out.println("imageLoc: "+ imageLoc);
		if (!imageLoc.equals("EMPTY_LOCATION")){
			WebClient webClient = WebClient.create(imageLoc);
			byte[] image = webClient.get()
					.accept(MediaType.IMAGE_JPEG)
					.retrieve()
					.bodyToMono(byte[].class)
					.block();
			System.out.println("Returning image from bucket");
			return image;
		}
		URL in = getClass().getClassLoader().getResource("404.png");

		try {
			return IOUtils.toByteArray(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**@GetMapping(value = "/**")
	public String fileNotFound() {
		return "Does not exist";
	}**/


}
