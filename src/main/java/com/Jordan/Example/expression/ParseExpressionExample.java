package com.Jordan.Example.expression;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ParseExpressionExample {

    public static void main(String[] argv) {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("JavaScript");
        try {
            System.out.println(engine.eval("80 / (190 * 190)"));
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(engine.eval("a - b"));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
