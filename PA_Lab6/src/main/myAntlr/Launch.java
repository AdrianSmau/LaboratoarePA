package main.myAntlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;

public class Launch {
    public static void main(String[] args) {
        System.out.println("Entering main Launch Function!...");
    }
    public Object parse(String input){
        CharStream str = CharStreams.fromString(input);
        Lexer lexer = new Java8Lexer(str);
        CommonTokenStream token = new CommonTokenStream(lexer);
        Java8Parser parser = new Java8Parser(token);
        ParseTree tree = parser.parse();

        MyVisitor visitor = new MyVisitor();
        return visitor.visit(tree);
    }
}
