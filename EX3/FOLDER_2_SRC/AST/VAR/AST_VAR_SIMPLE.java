package AST.VAR;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import Auxillery.Util;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.TYPE;

public class AST_VAR_SIMPLE extends AST_VAR
{
	/************************/
	/* simple variable name */
	/************************/
	public String name;
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_VAR_SIMPLE(String name)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
	
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("var -> ID( %s )\n",name);

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
		this.name = name;
	}

	/**************************************************/
	/* The printing message for a simple var AST node */
	/**************************************************/
	public void PrintMe()
	{
		/**********************************/
		/* AST NODE TYPE = AST SIMPLE VAR */
		/**********************************/
		System.out.format("AST NODE SIMPLE VAR( %s )\n",name);

		/*********************************/
		/* Print to AST GRAPHIZ DOT file */
		/*********************************/
		AST_GRAPHVIZ.getInstance().logNode(
			SerialNumber,
			String.format("SIMPLE VAR(%s)",name));
	}

	public TYPE SemantMe()
	{
		if(SYMBOL_TABLE.getInstance().find(name) == null){
			System.out.println(">> ERROR: variable " + name + " has not been defined\n");
			Util.printError(myLine);
		}
		TYPE t = SYMBOL_TABLE.getInstance().find(name);
		if(t.name.equals(this.name)){
			System.out.println("Error: static reference");
			Util.printError(myLine);
		}
		return t;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
