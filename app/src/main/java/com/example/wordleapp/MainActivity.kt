package com.example.wordleapp

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {
    var wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    //method to hide the keyboard
    fun hideKeyBoard(){
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val submit = findViewById<Button>(R.id.submit)
        val guessText: EditText  = findViewById(R.id.guessArea)
        var guess: String = guessText.getText().toString()
        val guess1 = findViewById<TextView>(R.id.guess1)
        val check1 = findViewById<TextView>(R.id.check1)
        val guess2 = findViewById<TextView>(R.id.guess2)
        val check2 = findViewById<TextView>(R.id.check2)
        val guess3 = findViewById<TextView>(R.id.guess3)
        val check3 = findViewById<TextView>(R.id.check3)
        val answer = findViewById<TextView>(R.id.answer)
        answer.text = "The answer is ${wordToGuess}"
        var noOfClicks = 0

        submit.setOnClickListener(){
            noOfClicks++
            if(noOfClicks == 1) {
                //change the text in the textview to the text in the edittext
                guess1.text = guessText.text
                //display the guess check on the next textview
                check1.text = checkGuess(guess1.text.toString().uppercase())
                //clear the edittext
                guessText.setText(" ")
                //hide the keyboard
                hideKeyBoard()


            }
            if(noOfClicks == 2) {
                //change the text in the textview to the text in the edittext
                guess2.text = guessText.text
                //display the guess check on the next textview
                check2.text = checkGuess(guess2.text.toString().uppercase())
                //clear the edittext
                guessText.setText(" ")
                //hide the keyboard
                hideKeyBoard()

            }
            if(noOfClicks == 3) {
                //change the text in the textview to the text in the edittext
                guess3.text = guessText.text
                //display the guess check on the next textview
                check3.text = checkGuess(guess3.text.toString().uppercase())
                //clear the edittext
                guessText.setText(" ")
                //hide the keyboard
                hideKeyBoard()
                //shows the correct answer
                answer.isVisible = true
            }
            if(noOfClicks > 3) {
                val toast = Toast.makeText(applicationContext, "You have exceeded three guesses", Toast.LENGTH_SHORT)
                toast.show()
            }






        }
    }

    //when the submit button is clicked
    //clear the textview



    //if the first guess is entered
    //display it on the first textview
    //if the second guess is enter display it on the second textview
    //if the thrid guess is entered displat on on the third textview
    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {

        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}