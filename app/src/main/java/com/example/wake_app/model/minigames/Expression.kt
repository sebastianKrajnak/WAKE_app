package com.example.wake_app.model.minigames

import kotlin.math.exp
import kotlin.random.Random

class Expression(val minVal : Int, val maxMal : Int) {
    private val _MAX_TERMS = 4;
    private val _MIN_TERMS = 2;

    private var _expr: String = "";

    val title = "Solve the expression"
    val expr: String?
        get() = _expr

    private var operators = listOf(
        "*",
        "+",
        "-"
    )

    init {
        this._expr = generateInfix();
    }

    fun getResult() : Int {
        return evaluate(toPostfix());
    }

    fun newExpression() {
        this._expr = generateInfix();
    }

    override fun toString(): String {
        return this._expr
    }

    /**
     * Evaluates postfix expression and returns its value
     */
    fun evaluate(postfix: String): Int {
        var s = ArrayDeque<Int>()
        var expr = postfix.split(" ").toTypedArray();

        for (c in expr) {

            if (!operators.contains(c)) s.addFirst(c.toInt());
            else {
                var op1 = s.removeFirst();
                var op2 = s.removeFirst();

                //System.out.println("calculating "+op2 + c + op1);
                var res: Int = 0;
                when (c) {
                    "+" -> res = op2 + op1;
                    "*" -> res = op2 * op1;
                    "-" -> res = op2 - op1;
                }
                s.addFirst(res);
            }
        }
        return s.removeFirst();
    }

    /**
     * Generates expression containing _MAX_TERMS of terms
     * from grammar :
     * E -> E
     * E -> E + E
     * E -> E * E
     * E -> E - E
     *
     *By applying right most derivation,
     * then generates int values in interval <minVal,maxVal> for each terminal E
     */
    private fun generateInfix() : String {
        val TERMINAL = "E";

        var s = ArrayDeque<String>()
        s.addFirst(TERMINAL)
        s.addFirst(operators[Random.nextInt(0,2)]);
        s.addFirst(TERMINAL)

        var termCnt = 1;

        while (termCnt != _MAX_TERMS) {
            val randRule = Random.nextInt(0,3);
            if (randRule == 3) break; //can break at random to generate <1,_MAX_TERMS> expression

            s.addFirst(operators[randRule]);
            s.addFirst(TERMINAL);
            termCnt++;
        }

        var strExpr = "";
        for (c in s) {
            if (c == TERMINAL) strExpr += Random.nextInt(minVal,maxMal).toString();
            else strExpr += c;

            strExpr += " ";
        }
        strExpr = strExpr.dropLast(1); //remove last space

        return strExpr;
    }

    /**
     * Returns precedence of operator c given it is in stack
     */
    private fun inPrecedence(c: String): Int {
        if (c == "+" || c == "-") return 2;
        return 4; //* precedence
    }

    /**
     * Returns precednce of operator c given it is outside stack
     */
    private fun outPrecedence(c: String): Int {
        if (c == "+" || c == "-") return 1;
        return 3; //* precedence
    }

    /**
     * Converts expression from infix notation to postfix notation
     * eg. 1 + 2 * 3 -> 123*+
     */
    fun toPostfix() : String {
        var postfixExpr = "";
        var s = ArrayDeque<String>()
        var expr = _expr!!.split(" ").toTypedArray();

        for (c in expr) {
            if (!operators.contains(c)) postfixExpr += c + " "; //operand
            else {
                if (s.isEmpty() || outPrecedence(c) > inPrecedence(s.first())) s.addFirst(c);
                else {
                    while (!s.isEmpty() && outPrecedence(c) < inPrecedence(s.first())) {
                        postfixExpr += s.first() + " ";
                        s.removeFirst();
                    }
                    s.addFirst(c);
                }
            }
        }
        while (!s.isEmpty()) {
            postfixExpr += s.first() + " ";
            s.removeFirst();
        } //poping remaining operators
        postfixExpr = postfixExpr.dropLast(1); //remove last whitespace

        return postfixExpr;
    }

}