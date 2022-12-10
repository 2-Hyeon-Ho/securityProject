import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
public class RestTemplateTest {
    private UriComponents uriComponents;
    private UriComponents uriComponentsAll;
    private final RestTemplate restTemplate = new RestTemplate();

    @BeforeEach
    public void setup(){
        uriComponents =  UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("jsonplaceholder.typicode.com")
                .path("/posts/{id}")
                .build()
                .expand(1l);

        uriComponentsAll =  UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("jsonplaceholder.typicode.com")
                .path("/posts")
                .build();
    }
    @Test
    @DisplayName("getForObject id:1 조회")
    public void getPostForObject(){
        Post post  = restTemplate.getForObject(uriComponents.toUri(),Post.class);
        log.debug("post:{}",post);
        Assertions.assertThat(post.getId()).isEqualTo(1l);
    }
    @Test
    @DisplayName("getForObject : all")
    public void getAllPostForObject(){

        Post[] posts = restTemplate.getForObject(uriComponentsAll.toUri(),Post[].class);
        for(Post post : posts){
            log.debug("post:{}",post);
        }
        Assertions.assertThat(posts.length).isEqualTo(100);
    }
    @Test
    @DisplayName("getForEntity id:1 조회")
    public void getForEntity(){
        ResponseEntity<Post> responseEntity = restTemplate.getForEntity(uriComponents.toUri(),Post.class);
        log.debug("post:{}", responseEntity.getBody());
        log.debug("status:{}",responseEntity.getStatusCode());
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    @DisplayName("getForEntity all")
    public void getAllPostForEntity(){
        ResponseEntity<Post[]> responseEntity = restTemplate.getForEntity(uriComponentsAll.toUri(),Post[].class);
        for(Post post : responseEntity.getBody()){
            log.debug("post:{}",post);
        }
        log.debug("status:{}",responseEntity.getStatusCode());
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertThat(responseEntity.getBody().length).isEqualTo(100);
    }
    @Test
    @DisplayName("exchange id:1")    public void exchange(){

        HttpHeaders headers = new HttpHeaders();
        headers.add("token","access token");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<Post> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, httpEntity, Post.class);

        log.debug("post:{}", responseEntity.getBody());
        log.debug("status:{}",responseEntity.getStatusCode());
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    @DisplayName("exchange all")    public void exchangeAll(){

        HttpHeaders headers = new HttpHeaders();
        headers.add("token","access token");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<Post[]> responseEntity = restTemplate.exchange(uriComponentsAll.toUri(), HttpMethod.GET, httpEntity, Post[].class);
        for(Post post : responseEntity.getBody()){
            log.debug("post:{}",post);
        }
        log.debug("status:{}",responseEntity.getStatusCode());
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertThat(responseEntity.getBody().length).isEqualTo(100);
    }}

@Getter
@ToString
class Post{
    private  long userId;
    private  long id;
    private  String title;
    private  String body;
}
