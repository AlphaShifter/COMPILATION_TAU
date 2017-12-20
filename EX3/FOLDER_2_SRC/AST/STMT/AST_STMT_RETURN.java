package AST.STMT;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import AST.EXP.AST_EXP;

public class AST_STMT_RETURN extends AST_STMT
{
	public AST_EXP res;

	/*******************/
	/*  CONSTRUCTOR(S) */
	/*******************/
	public AST_STMT_RETURN(AST_EXP res)
	{

		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (res != null) System.out.print("stmt -> RETURN exp SEMICOLON\n");
		if (res == null) System.out.print("stmt -> RETURN SEMICOLON\n");

		this.res = res;
		right = res;
	}

	public void PrintMe(){
		System.out.print("AST NODE STMT RETURN\n");
		if(res != null) res.PrintMe();
		AST_GRAPHVIZ.getInstance().logNode(SerialNumber,"STMT RETURN");
		if(res != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber,res.SerialNumber);
	}


	@Override
    public TYPE SemantMe(){
    	TYPE t=null;
    	if(res != null){
    		t = res.SemantMe();//TODO - maybe check if t is null
    		if(Util.isA(t,func_type)){
				return t;
			}
    	}
		else{
			if(func_type == TYPE_VOID.getInstance()){
    			return null;
    		}
		} 
		Util.printError(this.myLine);
		return null;
	}


}