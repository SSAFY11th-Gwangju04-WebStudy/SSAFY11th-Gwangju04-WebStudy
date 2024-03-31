package hello.board.repository;

import hello.board.domain.Post;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {
    private static Map<Long, Post> store = new HashMap<>();
    private static Long sequence = 0L;

    public Post save(Post post){
        post.setId(++sequence);
        post.setCreatedAt(LocalDateTime.now());
        store.put(post.getId(), post);
        return post;
    }

    public List<Post> findAll(){
        return new ArrayList<>(store.values());
    }

    public Post findById(Long id) {
        return store.get(id);
    }

    public void update(Long id, Post updateparam) {
        Post post = findById(id);
        post.setTitle(updateparam.getTitle());
        post.setContent(updateparam.getContent());
    }

    public void delete(Long id) {
        store.remove(id);
    }

}
