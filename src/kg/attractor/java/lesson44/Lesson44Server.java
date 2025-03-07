package kg.attractor.java.lesson44;

import com.sun.net.httpserver.HttpExchange;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import kg.attractor.java.server.BasicServer;
import kg.attractor.java.server.ContentType;
import kg.attractor.java.server.ResponseCodes;
import kg.attractor.java.utils.DataLoader;
import kg.attractor.java.utils.Utils;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Lesson44Server extends BasicServer {
    private final static Configuration freemarker = initFreeMarker();
    private List<Book> books = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private SampleDataModel sampleDataModel = new SampleDataModel();

    public Lesson44Server(String host, int port) throws IOException {
        super(host, port);
        registerGet("/sample", this::freemarkerSampleHandler);
        registerGet("/books", this::booksHandler);
        registerGet("/bookinfo", this::bookInfoHandler);
        registerGet("/employee", this::employeesHandler);

        initTestData();
    }

    private static Configuration initFreeMarker() {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            // путь к каталогу в котором у нас хранятся шаблоны
            // это может быть совершенно другой путь, чем тот, откуда сервер берёт файлы
            // которые отправляет пользователю
            cfg.setDirectoryForTemplateLoading(new File("data"));

            // прочие стандартные настройки о них читать тут
            // https://freemarker.apache.org/docs/pgui_quickstart_createconfiguration.html
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
            return cfg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void freemarkerSampleHandler(HttpExchange exchange) {
        Map<String, Object> data = getSampleDataModel();
        renderTemplate(exchange, "sample.ftlh", data);
    }

    protected void renderTemplate(HttpExchange exchange, String templateFile, Object dataModel) {
        try {
            // Загружаем шаблон из файла по имени.
            // Шаблон должен находится по пути, указанном в конфигурации
            Template temp = freemarker.getTemplate(templateFile);

            // freemarker записывает преобразованный шаблон в объект класса writer
            // а наш сервер отправляет клиенту массивы байт
            // по этому нам надо сделать "мост" между этими двумя системами

            // создаём поток, который сохраняет всё, что в него будет записано в байтовый массив
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            // создаём объект, который умеет писать в поток и который подходит для freemarker
            try (OutputStreamWriter writer = new OutputStreamWriter(stream)) {

                // обрабатываем шаблон заполняя его данными из модели
                // и записываем результат в объект "записи"
                temp.process(dataModel, writer);
                writer.flush();

                // получаем байтовый поток
                byte[] data = stream.toByteArray();

                // отправляем результат клиенту
                sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, data);
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private void initTestData() {
        books.add(new Book("1", "1984", "George Orwell", "Dystopian", false, "data/images/1.jpg", "Роман-антиутопия, описывающий тоталитарное общество под постоянным наблюдением."));
        books.add(new Book("2", "To Kill a Mockingbird", "Harper Lee", "Fiction", true, "data/images/1.jpg", "История о расовой несправедливости и моральной силе в маленьком городке."));
        books.add(new Book("3", "The Great Gatsby", "F. Scott Fitzgerald", "Classic", false, "data/images/1.jpg", "Какое-то описание книги, очень длинный текст"));

        Employee emp1 = new Employee("1", "John Doe", "john@example.com", "123123");
        emp1.borrowBook(books.get(1).getId());
        employees.add(emp1);
    }

    private void booksHandler(HttpExchange exchange) {
        Map<String, Object> data = new HashMap<>();
        data.put("books", books);
        renderTemplate(exchange, "books.ftlh", data);
    }

    private void bookInfoHandler(HttpExchange exchange) {
        try {
            String query = exchange.getRequestURI().getQuery();
            Map<String, String> params = Utils.parseUrlEncoded(query, "&");
            String bookId = params.get("id");

            Optional<Book> bookOpt = books.stream()
                    .filter(b -> b.getId().equals(bookId))
                    .findFirst();

            if (bookOpt.isPresent()) {
                Book book = bookOpt.get();
                Map<String, Object> data = new HashMap<>();
                data.put("book", book);
                renderTemplate(exchange, "bookinfo.ftlh", data);
            } else {
                sendResponse(exchange, "Книга не найдена.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, "Ошибка при обработке запроса.");
        }
    }

    private void employeesHandler(HttpExchange exchange){
        try {
        employees = DataLoader.loadEmployees("data/json/employees.json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("employees", employees);
        data.put("books", books);
        renderTemplate(exchange, "employee.ftlh", data);
    }

    private Map<String, Object> getSampleDataModel() {
        Map<String, Object> data = new HashMap<>();
        data.put("user", new SampleDataModel.User("Alex", "Attractor"));
        data.put("currentDateTime", LocalDateTime.now());
        data.put("customers", List.of(
                new SampleDataModel.User("Marco"),
                new SampleDataModel.User("Winston", "Duarte"),
                new SampleDataModel.User("Amos", "Burton", "'Timmy'")
        ));
        return data;
    }
}
