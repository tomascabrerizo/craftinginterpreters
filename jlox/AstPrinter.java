package com.lox;

import java.lang.StringBuilder;

class AstPrinter implements Expr.Visitor<String> {
   
    String print(Expr expr) {
        return expr.accept(this);
    } 
    
    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parethesize(expr.operator.lexeme, expr.left, expr.right);
    }
    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parethesize("group", expr.expression);
    }
    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if(expr.value == null) return "nil";
        return expr.value.toString();
    }
    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parethesize(expr.operator.lexeme, expr.right);
    }
    
    @Override
    public String visitVariableExpr(Expr.Variable expr)
    {
        //TODO: Function not implemented! 
        return null;
    }

    @Override
    public String visitAssignExpr(Expr.Assign expr) {
        //TODO: Function not implemented! 
        return null;
    }

    @Override
    public String visitLogicalExpr(Expr.Logical expr) {
        //TODO: Function not implemented! 
        return null;
    }
    
    @Override
    public String visitCallExpr(Expr.Call expr) {
        //TODO: Function not implemented! 
        return null;
    }

    @Override
    public String visitGetExpr(Expr.Get expr) {
        //TODO: Function not implemented! 
        return null;
    }
    
    @Override
    public String visitSetExpr(Expr.Set expr) {
        //TODO: Function not implemented! 
        return null;
    }
    
    @Override
    public String visitThisExpr(Expr.This expr) {
        //TODO: Function not implemented! 
        return null;
    }

    private String parethesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();
        
        builder.append("(").append(name);
        for(Expr expr :exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");
        return builder.toString();
    }

    public static void main(String[] args) {
        Expr expression = new Expr.Binary(
            new Expr.Unary(
                new Token(TokenType.MINUS, "-", null, 1),
                new Expr.Literal(123)),
            new Token(TokenType.STAR, "*", null, 1),
            new Expr.Grouping(
                new Expr.Literal(45.67)));

        System.out.println(new AstPrinter().print(expression));
    }

}
