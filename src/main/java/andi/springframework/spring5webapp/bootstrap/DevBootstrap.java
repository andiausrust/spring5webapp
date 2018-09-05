package andi.springframework.spring5webapp.bootstrap;

import andi.springframework.spring5webapp.model.Author;
import andi.springframework.spring5webapp.model.Book;
import andi.springframework.spring5webapp.model.Publisher;
import andi.springframework.spring5webapp.repositories.AuthorRepository;
import andi.springframework.spring5webapp.repositories.BookRepository;
import andi.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository,
                        BookRepository bookRepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Publisher publisher = new Publisher();
        publisher.setName("Frodo");

        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain driven design", "1234",publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author john = new Author("Jone ", "News");
        Book noeBJ = new Book("Web driven design", "8234", publisher);
        eric.getBooks().add(noeBJ);
        ddd.getAuthors().add(john);

        authorRepository.save(john);
        bookRepository.save(noeBJ);

    }

}
