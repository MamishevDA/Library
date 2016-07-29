package main.java.ru.mamishev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

/**
 * Created by MamishevDA on 24.07.2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan({"main.java.ru.mamishev", "main.java.ru.mamishev.controller"})
//@Import({ AppSecurityConfig.class })
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:~/workspace/h2/spring-noxmal;DB_CLOSE_ON_EXIT=TRUE;TRACE_LEVEL_FILE=4;AUTO_SERVER=TRUE");
        ds.setUsername("sa");
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplateOperations() {
        System.out.println("inside bean JdbcTemplate");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        jdbcTemplate.execute("DROP ALL OBJECTS");
        jdbcTemplate.execute("CREATE TABLE users (\n" +
                "  id bigint auto_increment PRIMARY KEY,\n" +
                "  name VARCHAR(30),\n" +
                "  pwd VARCHAR(30),\n" +
                "  id_role INTEGER\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE user_roles\n" +
                "(\n" +
                "    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "    role_name VARCHAR2(20)\n" +
                ");\n" +
                "CREATE UNIQUE INDEX \"User_roles_id_uindex\" ON User_roles (id);\n" +
                "CREATE UNIQUE INDEX \"User_roles_role_name_uindex\" ON User_roles (role_name);\n" +
                "CREATE TABLE books (\n" +
                "  id bigint auto_increment PRIMARY KEY,\n" +
                "  ISN VARCHAR(300),\n" +
                "  author  VARCHAR(500),\n" +
                "  title VARCHAR (500),\n" +
                "  takedByUser INTEGER\n" +
                ");\n" +
                "ALTER TABLE PUBLIC.BOOKS\n" +
                "  ADD CONSTRAINT BOOKS_USERS__fk\n" +
                "FOREIGN KEY (TAKEDBYUSER) REFERENCES USERS (ID);");
        jdbcTemplate.execute("INSERT INTO users VALUES (1, 'sa','sa',2);\n" +
                "INSERT INTO users VALUES (2, 'admin','admin',2);\n" +
                "INSERT INTO users VALUES (3, 'user','user',1);\n" +
                "INSERT INTO users VALUES (4, 'super_user','super_user',3);\n");
        jdbcTemplate.execute("INSERT INTO user_roles(role_name) VALUES ('ROLE_USER');\n" +
                "INSERT INTO user_roles(role_name) VALUES ('ROLE_ADMIN');\n" +
                "INSERT INTO user_roles(role_name) VALUES ('ROLE_SUPERADMIN');\n");

        jdbcTemplate.execute("\n" +
                "insert into books values(1,9537433,'Лев Толстой','Война и мир',1);\n" +
                "insert into books values(2,6802908,'Джейм Джойс','Улисс',2);\n" +
                "insert into books values(3,3874845,'Владимир Набоков','Лолита',3);\n" +
                "insert into books values(4,5568460,'Уильям Фолкнер','Шум и ярость',4);\n" +
                "insert into books values(5,6364470,'Ральф Эллисон','Невидимка',null);\n" +
                "insert into books values(6,6219409,'Вирджиния Вульф','К маяку',1);\n" +
                "insert into books values(7,5563838,'Гомер','Иллиада и Одиссея',null);\n" +
                "insert into books values(8,4042085,'Джейн Остин','Гордость и предубеждение',4);\n" +
                "insert into books values(9,5231263,'Данте Алигьери','Божественная комедия',null);\n" +
                "insert into books values(10,2753123,'Джоффри Чосер','Кентерберийские рассказы',null);\n" +
                "insert into books values(11,5460175,'Джонатан Свифт','Путешествия Гулливера',null);\n" +
                "insert into books values(12,7204938,'Джордж Элиот','Миддлмарч',null);\n" +
                "insert into books values(13,6721395,'Чинуа Ачебе','Распад',null);\n" +
                "insert into books values(14,9065950,'Джером Д. Сэлинджер','Над пропастью во ржи',null);\n" +
                "insert into books values(15,7301620,'Маргарет Митчелл','Унесенные ветром',null);\n" +
     /*            "insert into books values(16,3814905,'Габриэль Гарсия Маркес','Сто лет одиночества',null);\n" +
                "insert into books values(17,5977187,'Фрэнсис Скотт Фитцджеральд','Великий Гэтсби',null);\n" +
                "insert into books values(18,2421919,'Джозеф Хеллер','Уловка-22',null);\n" +
                "insert into books values(19,6031018,'Тони Моррисон','Возлюбленная',null);\n" +
                "insert into books values(20,6622321,'Джон Стейнбек','Грозди гнева',null);\n" +
                "insert into books values(21,6387908,'Салман Рушди','Дети полуночи',null);\n" +
                "insert into books values(22,6426597,'Олдос Хаксли','Дивный новый мир',null);\n" +
               "insert into books values(23,7687292,'Вирджиния Вульф','Миссис Дэллоуэй',null);\n" +
                "insert into books values(24,3330124,'Ричард Райт','Сын Америки',null);\n" +
                "insert into books values(25,5967924,'Алексис де Токвиль','Демократия в Америке',null);\n" +
                "insert into books values(26,5894455,'Чарльз Дарвин','Происхождение видов',null);\n" +
                "insert into books values(27,3560993,'Геродот','История',null);\n" +
                "insert into books values(28,7363721,'Жан-Жак Руссо','Общественный договор',null);\n" +
                "insert into books values(29,4273014,'Карл Маркс','Капитал',null);\n" +
                "insert into books values(30,6735645,'Никколо Макиавелли','Государь',null);\n" +
                "insert into books values(31,2051634,'Блаженный Августин','Исповедь',null);\n" +
                "insert into books values(32,6130989,'Томас Гоббс','Левиафан',null);\n" +
                "insert into books values(33,5024329,'Фукидид','История Пелопонесской войны',null);\n" +
                "insert into books values(34,9808644,'Джон Толкиен','Властелин колец',null);\n" +
                "insert into books values(35,1024253,'Алан Александр Милн','Винни-Пух',null);\n" +
                "insert into books values(36,5184261,'Клайв Льюис','Лев, колдунья и платяной шкаф',null);\n" +
                "insert into books values(37,6737387,'Эдуард Форстер','Поездка в Индию',null);\n" +
                "insert into books values(38,2130314,'Джон Керуак','В дороге',null);\n" +
                "insert into books values(39,9580879,'Харпер Ли','Убить пересмешника',null);\n" +
                "insert into books values(40,5846039,'Нет данных','Библия',null);\n" +
                "insert into books values(41,8645780,'Энтони Берджесс','Заводной апельсин',null);\n" +
                "insert into books values(42,5146826,'Уильям Фолкнер','Свет в августе',null);\n" +
                "insert into books values(43,4210211,'Уильям Дю Бойс','Души черного народа',null);\n" +
                "insert into books values(44,9237411,'Джин Рис','Безбрежное Саргассово море',null);\n" +
                "insert into books values(45,3859043,'Гюстав Флобер','Мадам Бовари',null);\n" +
                "insert into books values(46,3576418,'Джон Милтон','Потерянный рай',null);\n" +
                "insert into books values(47,437641,'Лев Толстой','Анна Каренина',null);\n" +
                "insert into books values(48,3175538,'Уильям Шекспир','Гамлет',null);\n" +
                "insert into books values(49,2315365,'Уильям Шекспир','Король Лир',null);\n" +
                "insert into books values(50,2675904,'Уильям Шекспир','Отелло',null);\n" +
                "insert into books values(51,9986127,'Уильям Шекспир','Сонеты',null);\n" +
                "insert into books values(52,4763235,'Уолт Уитмен','Листья травы',null);\n" +
                "insert into books values(53,8497611,'Марк Твен','Приключения Гекльберри Финна',null);\n" +
                "insert into books values(54,697155,'Редьярд Киплинг','Ким',null);\n" +
                "insert into books values(55,8193952,'Мэри Шелли','Франкенштейн',null);\n" +
                "insert into books values(56,1851713,'Тони Моррисон','Песнь Соломона',null);\n" +
                "insert into books values(57,3761062,'Кен Кизи','Пролетая над гнездом кукушки',null);\n" +
                "insert into books values(58,9868239,'Эрнест Хемингуэй','По ком звонит колокол',null);\n" +
                "insert into books values(59,6641616,'Курт Воннегут','Бойня номер пять',null);\n" +
                "insert into books values(60,5910341,'Джордж Оруэлл','Скотный двор',null);\n" +
                "insert into books values(61,1804991,'Уильям Голдинг','Повелитель мух',null);\n" +
                "insert into books values(62,1538342,'Труман Капоте','Хладнокровное убийство',null);\n" +
                "insert into books values(63,9734915,'Дорис Лессинг','Золотая тетрадь',null);\n" +
                "insert into books values(64,3426249,'Марсель Пруст','В поисках потерянного времени',null);\n" +
                "insert into books values(65,6801850,'Реймонд Чандлер','Большой сон',null);\n" +
                "insert into books values(66,2970654,'Уильям Фолкнер','Когда я умирала',null);\n" +
                "insert into books values(67,2700075,'Эрнест Хемингуэй','Фиеста',null);\n" +
                "insert into books values(68,8191654,'Роберт Грейвз','Я, Клавдий',null);\n" +
                "insert into books values(69,2714490,'Карсон Маккаллерс','Сердце — одинокий охотник',null);\n" +
                "insert into books values(70,9543049,'Дэвид Лоуренс','Сыновья и любовники',null);\n" +
                "insert into books values(71,7176759,'Роберт Пенн Уоррен','И вся королевская рать',null);\n" +
                "insert into books values(72,9757801,'Джеймс Болдуин','Иди, вещай с горы',null);\n" +
                "insert into books values(73,8473102,'Эдвин Уайт','Паутина Шарлотты',null);\n" +
                "insert into books values(74,8554422,'Джозеф Конрад','Сердце тьмы',null);\n" +
                "insert into books values(75,4233268,'Эли Визель','Ночь',null);\n" +
                "insert into books values(76,9532199,'Джон Апдайк','Кролик, беги',null);\n" +
                "insert into books values(77,2004094,'Эдит Уортон','Эпоха невинности',null);\n" +
                "insert into books values(78,6767463,'Филип Рот','Случай портного',null);\n" +
                "insert into books values(79,9822145,'Теодор Драйзер','Американская трагедия',null);\n" +
                "insert into books values(80,212429,'Натаниэль Уэст','День саранчи',null);\n" +
                "insert into books values(81,6441810,'Генри Миллер','Тропик рака',null);\n" +
                "insert into books values(82,6341848,'Дэшиел Хэммет','Мальтийский сокол',null);\n" +
                "insert into books values(83,8005358,'Филип Пулман','Темные начала',null);\n" +
                "insert into books values(84,1043415,'Уилла Кейтер','Смерть пришла архиепископу',null);\n" +
                "insert into books values(85,9653489,'Зигмунд Фрейд','Толкования снов',null);\n" +
                "insert into books values(86,5963532,'Генри Адамс','Воспитание Генри Адамса',null);\n" +
                "insert into books values(87,6179079,'Мао Цзедун','Цитатник',null);\n" +
                "insert into books values(88,1408633,'Уильям Джемс','Многообразие религиозного опыта',null);\n" +
                "insert into books values(89,8801032,'Ивлин Во','Возвращение в Брайдсхед',null);\n" +
                "insert into books values(90,8685032,'Рейчел Карсон','Безмолвная весна',null);\n" +
                "insert into books values(91,9014595,'Джон Кейнс','Общая теория занятости, процента и денег',null);\n" +
                "insert into books values(92,3145801,'Джозеф Конрад','Лорд Джим',null);\n" +
                "insert into books values(93,5426756,'Роберт Грейвз','Простимся со всем этим',null);\n" +
                "insert into books values(94,3052889,'Джон Кеннет Гэлбрейт','Общество изобилия',null);\n" +
                "insert into books values(95,3793552,'Кеннет Грэм','Ветер в ивах',null);\n" +
                "insert into books values(96,9785656,'Алекс Хейли и Малькольм Икс','Автобиография Малькольма Икс',null);\n" +
                "insert into books values(97,9647438,'Литтон Стречи','Выдающиеся викторианцы',null);\n" +
                "insert into books values(98,3855659,'Элис Уокер','Цвет пурпурный',null);\n" +*/
                "insert into books values(99,7979202,'Уинстон Черчилль','Вторая мировая война',null);");
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}