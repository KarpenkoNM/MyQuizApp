package com.example.myquizapp

object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    const val LEVEL: String = "level"
    const val LIVES: String = "lives"
    const val IS_WINNER: String = "is_winner"
    const val MAX_POINTS_PER_LEVEL: Int = 5 // Максимальные очки для перехода на следующий уровень

    fun questionsLevel1(): ArrayList<Question> {
        return arrayListOf(
            Question(1, "Какой стране принадлежит этот флаг?", R.drawable.ic_flag_of_argentina, "Аргентина", "Австралия", "Армения", "Австрия", 1),
            Question(2, "Какой стране принадлежит этот флаг?", R.drawable.ic_flag_of_brazil, "Беларусь", "Белиз", "Бруней", "Бразилия", 4),
            Question(3, "Какой стране принадлежит этот флаг?", R.drawable.ic_flag_of_canada, "Канада", "Китай", "Куба", "Кипр", 1),
            Question(4, "Какой стране принадлежит этот флаг?", R.drawable.ic_flag_of_france, "Финляндия", "Франция", "Фиджи", "Фарерские острова", 2),
            Question(5, "Какой стране принадлежит этот флаг?", R.drawable.ic_flag_of_japan, "Иордан", "Япония", "Ямайка", "ни один из них", 2)
        )
    }

    fun questionsLevel2(): ArrayList<Question> {
        return arrayListOf(
            Question(1, "Какой стране принадлежит этот флаг?", R.drawable.ic_flag_of_germany, "Германия", "Грузия", "Греция", "ни один из них", 1),
            Question(2, "Какой стране принадлежит этот флаг?", R.drawable.ic_flag_of_india, "Исландия", "Иран", "Венгрия", "Индия", 4),
            Question(3, "Какой стране принадлежит этот флаг?", R.drawable.ic_flag_of_italy, "Исландия", "Италия", "Израиль", "Индонезия", 2),
            Question(4, "Какой стране принадлежит этот флаг?", R.drawable.ic_flag_of_mexico, "Малазия", "Мальта", "Мексика", "Монако", 3),
            Question(5, "Какой стране принадлежит этот флаг?", R.drawable.ic_flag_of_nigeria, "Норвегия", "Нигерия", "Нидерланды", "Непал", 2)
        )
    }

    fun questionsLevel3(): ArrayList<Question> {
        return arrayListOf(
            Question(1, "Какой стране принадлежит этот флаг?", R.drawable.ic_flag_of_denmark, "Доминикана", "Египет", "Дания", "Эфиопия", 3),
            Question(2, "Какой стране принадлежит этот флаг?", R.drawable.ic_flag_of_kuwait, "Кувэйт", "Иордан", "Судан", "Палестина", 1),
            Question(3, "Какой стране принадлежит этот флаг?", R.drawable.ic_flag_of_brazil, "Румыния", "Россия", "Руанда", "ни один из них", 4),
            Question(4, "Какой стране принадлежит этот флаг?", R.drawable.ic_flag_of_belgium, "Бельгия", "Сербия", "Сингапур", "Словакия", 1),
            Question(5, "Какой стране принадлежит этот флаг?", R.drawable.ic_flag_of_new_zealand, "Австралия", "Новая зеландия", "Острова Святой Елены", "Монтсеррат", 2)
        )
    }
}