// Generated from C:/Users/adria/Desktop/PA/PA_Lab6/src/main/myAntlr\Java8.g4 by ANTLR 4.9.1
package main.myAntlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Java8Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Java8Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Java8Parser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(Java8Parser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java8Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(Java8Parser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java8Parser#drawcmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrawcmd(Java8Parser.DrawcmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java8Parser#deletecmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeletecmd(Java8Parser.DeletecmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java8Parser#freehandcmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFreehandcmd(Java8Parser.FreehandcmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java8Parser#resetcmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResetcmd(Java8Parser.ResetcmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java8Parser#exitcmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExitcmd(Java8Parser.ExitcmdContext ctx);
}