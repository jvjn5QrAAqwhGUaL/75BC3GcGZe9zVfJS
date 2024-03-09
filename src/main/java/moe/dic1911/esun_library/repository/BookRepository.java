package moe.dic1911.esun_library.repository;

import moe.dic1911.esun_library.data.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

    Book getBookByIsbn(String isbn);

}
