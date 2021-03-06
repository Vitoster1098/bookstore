package ru.ystu.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.ystu.book.entity.Book;
import ru.ystu.book.repository.BookRepository;
import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class BookConfiguration {
    @Autowired
    private BookRepository bookRep;

    @PostConstruct
    public void init(){
        bookRep.save(new Book(1L, "Путешествие в интернет", "Удивительное приключение, где главный герой с его " +
                "верными друзьями решаются покорить луну.", "01.03.2017", 768));
        bookRep.save(new Book(2L, "Путешествие в комментарии", "Обычный день. Обычные видео. " +
                "Необычные люди с комментариями.", "13.05.2018", 799));
        bookRep.save(new Book(3L, "Топ 10 аниме про школу", "Если ты ищещь аниме с жанром повседневность - " +
                "ты пытаешься вернуть через аниме то, чего у тебя не было в школе.", "10.10.1998", 1500));
        bookRep.save(new Book(4L, "Самоучитель мужского вокала", "Потрясающий самоучитель мужского " +
                "вокала. Примените его у друга на свадьбе, в караоке с друзьями или в качалке.", "29.04.2007", 3000));
        bookRep.save(new Book(5L, "Лучшие рецензии на пиццу", "Это книга, в которой собраны лучшие " +
                "рецензии авторитетных, официальных критиков со всего мира. Прочти их мнение и выбери для себя лучшую пиццу!",
                "25.11.2050", 399));
    }
}