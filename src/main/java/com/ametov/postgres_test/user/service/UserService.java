package com.ametov.postgres_test.user.service;

import com.ametov.postgres_test.user.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
public class UserService {
    private static final String[] maleNames = {

            "Александр", "Иван", "Артем", "Дмитрий", "Николай",

            "Алексей", "Максим", "Сергей", "Виктор", "Павел",

            "Григорий", "Владимир", "Илья", "Станислав", "Роман",

            "Андрей", "Валентин", "Петр", "Михаил", "Василий",

            "Вадим", "Егор", "Платон", "Глеб", "Всеволод",

            "Константин", "Анатолий", "Валерий", "Олег", "Арсений",

            "Виталий", "Петр", "Милан", "Геннадий", "Евгений",

            "Владислав", "Валериян", "Денис", "Вера", "Руслан",

            "Павел", "Борис", "Родион", "Тимур", "Платон",

            "Игнат", "Юрий", "Валерий", "Святослав", "Валерий",

            "Федор", "Никита", "Семен", "Ярослав", "Влад",

            "Артур", "Эдуард", "Антон", "Тимофей", "Игорь",

            "Даниил", "Руслан", "Степан", "Богдан", "Владислав",

            "Герман", "Илья", "Андрей", "Данила", "Матвей",

            "Максим", "Игорь", "Алексей", "Ярослав", "Евгений",

            "Семен", "Артем", "Анатолий", "Глеб", "Артур",

            "Александр", "Михаил", "Иван", "Павел", "Дмитрий",

            "Никита", "Андрей", "Сергей", "Алексей", "Владимир"

    };
    private static final String[] femaleNames = {

            "Мария", "Екатерина", "София", "Анна", "Ольга",

            "Татьяна", "Ирина", "Елена", "Алиса", "Надежда",

            "Ксения", "Милана", "Евгения", "Ангелина", "Людмила",

            "Арина", "Анжелика", "Ева", "Валентина", "Алина",

            "Светлана", "Маргарита", "Антонина", "Юлия", "Дарья",

            "Жанна", "Майя", "Эльвира", "Вера", "Лариса",

            "Валерия", "Наталья", "Юлиана", "Анисья", "Софья",

            "Анастасия", "Алена", "Виктория", "Ульяна", "Виолетта",

            "Регина", "Полина", "Есения", "Инна", "Раиса",

            "Виола", "Эмилия", "Лидия", "Галина", "Лилия",

            "Артемида", "Зинаида", "Василиса", "Яна", "Серафима",

            "Роза", "Тамара", "Фаина", "Лиана", "Диана",

            "Марина", "Лилиана", "Инесса", "Альбина", "Ярослава",

            "Эльза", "Луиза", "Регина", "Эвелина", "Наталия",

            "Эльмира", "Эвелина", "Антонина", "Снежана", "Мирослава",

            "Эльза", "Инга", "Юлиетта", "Розалия", "Зарина",

            "Лидия", "Марфа", "Лариса", "Оксана", "Эльмира",

            "Ярослава", "Лидия", "Маргарита", "Алевтина", "Розалия",

            "Нина", "Лилия", "Изольда", "Марина", "Ираида"

    };
    private static final String[] femaleLastName = {

            "Иванова", "Петрова", "Сидорова", "Кузнецова", "Смирнова",

            "Михайлова", "Федорова", "Соколова", "Яковлева", "Попова",

            "Андреева", "Новикова", "Морозова", "Волкова", "Алексеева",

            "Лебедева", "Семенова", "Егорова", "Павлова", "Козлова",

            "Степанова", "Николаева", "Орлова", "Антонова", "Григорьева",

            "Васнецова", "Денисова", "Тихонова", "Фомина", "Котова",

            "Виноградова", "Зайцева", "Макарова", "Архипова", "Белова",

            "Медведева", "Герасимова", "Крылова", "Титова", "Куликова",

            "Карпова", "Афанасьева", "Власова", "Филатова", "Прокофьева",

            "Жукова", "Баранова", "Гусева", "Калашникова", "Гришина",

            "Якушева", "Горбунова", "Кудрявцева", "Быкова", "Маслова",

            "Родионова", "Колесникова", "Сазонова", "Цветкова", "Дмитриева",

            "Королева", "Германа", "Ефимова", "Артемьева", "Захарова",

            "Савельева", "Трофимова", "Щербакова", "Логинова", "Голубева",

            "Панова", "Ширяева", "Яшинa", "Карасева", "Суркова",

            "Сорокинa", "Лазарева", "Воронина", "Сергеева", "Полякова",

            "Кондратьева", "Беляева", "Кузьмина", "Васильева", "Скворцова",

            "Мартынова", "Емельянова", "Горбачева", "Третьякова", "Бирюкова",

            "Шарапова", "Ларионова", "Белоусова", "Рожкова", "Игнатьева"

    };
    private static final String[] maleLastName = {

            "Иванов", "Петров", "Сидоров", "Кузнецов", "Смирнов",

            "Михайлов", "Федоров", "Соколов", "Яковлев", "Попов",

            "Андреев", "Новиков", "Морозов", "Волков", "Алексеев",

            "Лебедев", "Семенов", "Егоров", "Павлов", "Козлов",

            "Степанов", "Николаев", "Орлов", "Антонов", "Григорьев",

            "Васнецов", "Денисов", "Тихонов", "Фомин", "Котов",

            "Виноградов", "Зайцев", "Макаров", "Архипов", "Белов",

            "Медведев", "Герасимов", "Крылов", "Титов", "Куликов",

            "Карпов", "Афанасьев", "Власов", "Филатов", "Прокофьев",

            "Жуков", "Баранов", "Гусев", "Калашников", "Гришин",

            "Якушев", "Горбунов", "Кудрявцев", "Быков", "Маслов",

            "Родионов", "Колесников", "Сазонов", "Цветков", "Дмитриев",

            "Королев", "Герман", "Ефимов", "Артемьев", "Захаров",

            "Савельев", "Трофимов", "Щербаков", "Логинов", "Голубев",

            "Панов", "Ширяев", "Яшин", "Карасев", "Сурков",

            "Сорокин", "Лазарев", "Воронин", "Сергеев", "Поляков",

            "Кондратьев", "Беляев", "Кузьмин", "Васильев", "Скворцов",

            "Мартынов", "Емельянов", "Горбачев", "Третьяков", "Бирюков",

            "Шарапов", "Ларионов", "Белоусов", "Рожков", "Игнатьев"

    };

    public static UserEntity createUser() {
        Random random = new Random();
        boolean isMale = random.nextBoolean();
        String[] names = maleNames;
        String[] lastNames = maleLastName;
        if (!isMale) {
            names = femaleNames;
            lastNames = femaleLastName;
        }
        return UserEntity.builder()
                .firstName(names[random.nextInt(names.length)])
                .lastName(lastNames[random.nextInt(lastNames.length)])
                .build();
    }
}