// Generated from C:/Users/adria/Desktop/PA/PA_Lab6/src/main/myAntlr\Java8.g4 by ANTLR 4.9.1
package main.myAntlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Java8Parser}.
 */
public interface Java8Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Java8Parser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(Java8Parser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java8Parser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(Java8Parser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java8Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(Java8Parser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java8Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(Java8Parser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java8Parser#drawcmd}.
	 * @param ctx the parse tree
	 */
	void enterDrawcmd(Java8Parser.DrawcmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java8Parser#drawcmd}.
	 * @param ctx the parse tree
	 */
	void exitDrawcmd(Java8Parser.DrawcmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java8Parser#deletecmd}.
	 * @param ctx the parse tree
	 */
	void enterDeletecmd(Java8Parser.DeletecmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java8Parser#deletecmd}.
	 * @param ctx the parse tree
	 */
	void exitDeletecmd(Java8Parser.DeletecmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java8Parser#freehandcmd}.
	 * @param ctx the parse tree
	 */
	void enterFreehandcmd(Java8Parser.FreehandcmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java8Parser#freehandcmd}.
	 * @param ctx the parse tree
	 */
	void exitFreehandcmd(Java8Parser.FreehandcmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java8Parser#resetcmd}.
	 * @param ctx the parse tree
	 */
	void enterResetcmd(Java8Parser.ResetcmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java8Parser#resetcmd}.
	 * @param ctx the parse tree
	 */
	void exitResetcmd(Java8Parser.ResetcmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java8Parser#exitcmd}.
	 * @param ctx the parse tree
	 */
	void enterExitcmd(Java8Parser.ExitcmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java8Parser#exitcmd}.
	 * @param ctx the parse tree
	 */
	void exitExitcmd(Java8Parser.ExitcmdContext ctx);
}