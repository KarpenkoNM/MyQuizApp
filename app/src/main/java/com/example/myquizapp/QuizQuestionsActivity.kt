package com.example.myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.os.Handler
import android.os.Looper

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mUserName: String? = null
    private var mCorrectAnswers: Int = 0
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mLives: Int = 0
    private var mLevel: Int = 1
    private var mPoints: Int = 0

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null

    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null

    private var btnSubmit: Button? = null
    private var tvLives: TextView? = null
    private var tvLevel: TextView? = null
    private var tvPoints: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mLives = intent.getIntExtra(Constants.LIVES, 3)
        mLevel = intent.getIntExtra(Constants.LEVEL, 1)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)
        tvLives = findViewById(R.id.tv_lives)
        tvLevel = findViewById(R.id.tv_level)
        tvPoints = findViewById(R.id.tv_points)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        loadQuestionsForLevel(mLevel)
        setQuestion()
        updateUI()
    }

    private fun loadQuestionsForLevel(level: Int) {
        mQuestionsList = when (level) {
            1 -> Constants.questionsLevel1()
            2 -> Constants.questionsLevel2()
            3 -> Constants.questionsLevel3()
            else -> Constants.questionsLevel1()
        }
    }

    private fun setQuestion() {
        defaultOptionsView()
        val question: Question = mQuestionsList!![mCurrentPosition - 1]

        ivImage?.setImageResource(question.image)
        progressBar?.max = Constants.MAX_POINTS_PER_LEVEL
        progressBar?.progress = mPoints % Constants.MAX_POINTS_PER_LEVEL
        tvProgress?.text = "${mPoints % Constants.MAX_POINTS_PER_LEVEL}/${Constants.MAX_POINTS_PER_LEVEL}"
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = arrayListOf(tvOptionOne, tvOptionTwo, tvOptionThree, tvOptionFour)
        for (option in options) {
            option?.setTextColor(Color.parseColor("#7A8089"))
            option?.typeface = Typeface.DEFAULT
            option?.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_option_one -> tvOptionOne?.let { selectedOptionView(it, 1) }
            R.id.tv_option_two -> tvOptionTwo?.let { selectedOptionView(it, 2) }
            R.id.tv_option_three -> tvOptionThree?.let { selectedOptionView(it, 3) }
            R.id.tv_option_four -> tvOptionFour?.let { selectedOptionView(it, 4) }
            R.id.btn_submit -> handleAnswerSubmission()
        }
    }

    private fun handleAnswerSubmission() {
        if (mSelectedOptionPosition == 0) {
            Toast.makeText(this, "Пожалуйста, выберите ответ.", Toast.LENGTH_SHORT).show()
        } else {
            val question = mQuestionsList!![mCurrentPosition - 1]
            if (question.correctAnswer != mSelectedOptionPosition) {
                mLives--
                tvLives?.setTextColor(Color.RED)
                Handler(Looper.getMainLooper()).postDelayed({
                    tvLives?.setTextColor(Color.parseColor("#363A43"))
                }, 1000)

                answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)

                if (mLives == 0) {
                    navigateToResultActivity(false)
                    return
                }
            } else {
                mCorrectAnswers++
                mPoints++

                answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                if (mPoints % Constants.MAX_POINTS_PER_LEVEL == 0) {
                    mLevel++
                    tvLevel?.setTextColor(Color.GREEN)
                    Handler(Looper.getMainLooper()).postDelayed({
                        tvLevel?.setTextColor(Color.parseColor("#363A43"))
                    }, 1000)

                    if (mLevel > 3) {
                        navigateToResultActivity(true)
                        return
                    }
                    loadQuestionsForLevel(mLevel)
                    mCurrentPosition = 1
                } else {
                    mCurrentPosition++
                    if (mCurrentPosition > mQuestionsList!!.size) {
                        mCurrentPosition = 1
                    }
                }
            }

            mSelectedOptionPosition = 0
            Handler(Looper.getMainLooper()).postDelayed({
                setQuestion()
                updateUI()
            }, 1000)
        }
    }

    private fun updateUI() {
        tvLives?.text = "Жизни: $mLives"
        tvLevel?.text = "Уровень: $mLevel"
        tvPoints?.text = "Очки: $mPoints"
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> tvOptionOne?.background = ContextCompat.getDrawable(this, drawableView)
            2 -> tvOptionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            3 -> tvOptionThree?.background = ContextCompat.getDrawable(this, drawableView)
            4 -> tvOptionFour?.background = ContextCompat.getDrawable(this, drawableView)
        }
    }

    private fun navigateToResultActivity(isWinner: Boolean) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(Constants.USER_NAME, mUserName)
        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
        intent.putExtra(Constants.IS_WINNER, isWinner)
        startActivity(intent)
        finish()
    }
}