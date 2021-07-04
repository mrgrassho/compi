
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Integer;
import java_cup.runtime.Symbol;

%%

%class Lexer
%cupsym sym
%cup
%unicode
%line
%column
%init{
  try {
    file = new File("../ts.txt");
    bw = new BufferedWriter(new FileWriter(file, true));
    simbolos = new ArrayList<>();
    s = "";
  	Error = "";
  } catch (IOException e) {
    e.printStackTrace();
  }
%init}

%{
  BufferedWriter  bw;
  File file;
  ArrayList<String> simbolos;
  public String s;
  public String Error;

  public void writeTable(String str) throws IOException{
    if (!simbolos.contains(str.split(",")[0])) {
      bw.write(str);
      bw.newLine();
      bw.flush();
      simbolos.add(str.split(",")[0]);
    }
  }
%}

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]

Comentario   = "--/" [^*] ~"/--" | "--/" "/"+ "--"

/* Comienzan las definiciones de */

String = \"([a-zA-Záéíóú0-9 \t\f\.\!\¡ñÑ\s]{0,30})\"
Integer = [1-9][0-9]* | 0
Float = {Integer}\.[0-9]*
Boolean = "true"|"false"

Identificador = [:jletter:][:jletterdigit:]*

%%

<YYINITIAL> {
  /* Palabras Reservadas*/
  ":="                           { s += String.format("\n>>> Simbolo Asignacion encontrado en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.ASIGNACION, yychar, yyline);}
  ":"                           { s += String.format("\n>>> Simbolo Dos Puntos encontrado en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.DOS_PUNTOS, yychar, yyline);}
  ";"                           { s += String.format("\n>>> Simbolo Punto y Coma encontrado en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.PUNTO_Y_COMA, yychar, yyline);}
  ","                           { s += String.format("\n>>> Simbolo coma encontrado en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.COMA, yychar, yyline);}
  "=="                          { s += String.format("\n>>> Simbolo Igual encontrado en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.IGUAL, yychar, yyline);}
  "!="                          { s += String.format("\n>>> Simbolo Distinto encontrado en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.DISTINTO, yychar, yyline);}
  "!"                           { s += String.format("\n>>> Simbolo NOT encontrado en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.NOT, yychar, yyline);}
  ">"                           { s += String.format("\n>>> Simbolo Mayor encontrado en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.MAYOR, yychar, yyline);}
  "<"                           { s += String.format("\n>>> Simbolo Menor encontrado en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.MENOR, yychar, yyline);}
  "<="                          { s += String.format("\n>>> Simbolo MenorIgual encontrado en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.MENOR_IGUAL, yychar, yyline);}
  ">="                          { s += String.format("\n>>> Simbolo MayorIgual  encontrado en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.MAYOR_IGUAL, yychar, yyline);}
  "("                           { s += String.format("\n>>> Simbolo Parentesis Abre encontrado en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.PARENTESIS_ABRE, yychar, yyline);}
  ")"                           { s += String.format("\n>>> Simbolo Parentesis Cierra encontrado en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.PARENTESIS_CIERRA, yychar, yyline);}
  "{"                           { s += String.format("\n>>> Simbolo Llaves Abre encontrado en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.LLAVES_ABRE, yychar, yyline);}
  "}"                           { s += String.format("\n>>> Simbolo Llaves Cierra encontrado en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.LLAVES_CIERRA, yychar, yyline);}
  "&&"                          { s += String.format("\n>>> Simbolo AND: [%s] encontrado en linea %d, columna %d\n",yytext(), yyline, yycolumn); return new Symbol(sym.AND, yychar, yyline);}
  "||"                          { s += String.format("\n>>> Simbolo OR: [%s] encontrado en linea %d, columna %d\n",yytext() , yyline, yycolumn); return new Symbol(sym.OR, yychar, yyline);}
  "+"                           { s += String.format("\n>>> Simbolo Suma: [%s] encontrado en linea %d, columna %d\n",yytext() , yyline, yycolumn);return new Symbol(sym.SUMA, yychar, yyline);}
  "-"                           { s += String.format("\n>>> Simbolo Resta: [%s] encontrado en linea %d, columna %d\n",yytext() , yyline, yycolumn);return new Symbol(sym.RESTA, yychar, yyline);}
  "/"                           { s += String.format("\n>>> Simbolo Division: [%s] encontrado en linea %d, columna %d\n",yytext() , yyline, yycolumn);return new Symbol(sym.DIVISION, yychar, yyline);}
  "*"                           { s += String.format("\n>>> Simbolo Multiplicacion: [%s] encontrado en linea %d, columna %d\n",yytext() , yyline, yycolumn);return new Symbol(sym.MULTIPLICAION, yychar, yyline);}
  "?"                           { s += String.format("\n>>> Simbolo Signo pregunta de IF Unario: [%s] encontrado en linea %d, columna %d\n",yytext() , yyline, yycolumn);return new Symbol(sym.SIGNO_PREGUNTA, yychar, yyline);}
  "DISPLAY"                     { s += String.format("\n>>> Funcion encontrada en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.DISPLAY_FUNCTION, yychar, yyline);}
  "STRING"                      { s += String.format("\n>>> Funcion consta en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.TYPE_STRING, yychar, yyline);}
  "INT"                         { s += String.format("\n>>> Funcion encontrada en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.TYPE_INTEGER, yychar, yyline);}
  "FLOAT"                       { s += String.format("\n>>> Funcion encontrada en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.TYPE_FLOAT, yychar, yyline);}
  "BOOL"                        { s += String.format("\n>>> Funcion encontrada en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.TYPE_BOOL, yychar, yyline);}

  "WHILE"                       { s += String.format("\n>>> while en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.WHILE, yychar, yyline);}
  "IF"                          { s += String.format("\n>>> if en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.IF, yychar, yyline);}
  "ELSE"                        { s += String.format("\n>>> else en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.ELSE, yychar, yyline);}
  "DECLARE.SECTION"             { s += String.format("\n>>> DECLARE en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.DECLARE_SECTION, yychar, yyline);}
  "ENDDECLARE.SECTION"          { s += String.format("\n>>> ENDDECLARE en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.ENDDECLARE_SECTION, yychar, yyline);}
  "PROGRAM.SECTION"             { s += String.format("\n>>> PROGRAM en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.PROGRAM_SECTION, yychar, yyline);}
  "ENDPROGRAM.SECTION"          { s += String.format("\n>>> ENDPROGRAM en linea %d, columna %d\n", yyline, yycolumn); return new Symbol(sym.ENDPROGRAM_SECTION, yychar, yyline);}

  {String}                      {
                                  s += String.format("\n>>> String encontrado: [%s] en linea %d, columna %d\n", yytext(), yyline, yycolumn);
                                  return new Symbol(sym.CONST_STRING, yychar, yyline, new String(yytext()));
                                }
  {Integer}                     {
                                  if ((Integer.valueOf(yytext()) > -32768) && (Integer.valueOf(yytext()) < 32768)) {
                                    s += String.format("\n>>> Integer encontrado: [%s] en linea %d, columna %d\n", yytext(), yyline, yycolumn);
                                    return new Symbol(sym.CONST_INTEGER, yychar, yyline, new String(yytext()));
                                  } else {
                                    String exception_message = String.format(" ERROR - Integer excede los 16 bits: [%s] en linea %d, columna %d\n", yytext(), yyline, yycolumn);
                                    throw new Error(exception_message);
                                  }
                                }
  {Float}                       { /* real de 32 bits : 16 parte entera y 16 parte decimal*/
									                int indexDecimal = yytext().indexOf(".");
								                  String entero = yytext().substring(0, indexDecimal);
								                  String decimal = yytext().substring(indexDecimal+1,yytext().length());

                								  if ( (Integer.valueOf(entero) > -32768) && (Integer.valueOf(entero) < 32768)
                								       && (Integer.valueOf(decimal) > -32768) && (Integer.valueOf(decimal) < 32768) ) {
                                          s += String.format("\n>>> Float encontrado: [%s] en linea %d, columna %d\n", yytext(), yyline, yycolumn);
                                          return new Symbol(sym.CONST_FLOAT, yychar, yyline, new String(yytext()));
                                  } else {
                                    String exception_message = String.format(" ERROR - Float excede los 32 bits: [%s] en linea %d, columna %d\n", yytext(), yyline, yycolumn);
                                    throw new Error(exception_message);
                                  }
                                }
  {Boolean}                     {
                                  s += String.format("\n>>> Bool encontrado: [%s] en linea %d, columna %d\n", yytext(), yyline, yycolumn);
                                  return new Symbol(sym.CONST_BOOL, yychar, yyline, new String(yytext()));
                                }
  {Identificador}               {
                                  s += String.format("\n>>> Identificador encontrado: [%s] en linea %d, columna %d\n", yytext(), yyline, yycolumn);
                                  return new Symbol(sym.ID, yychar, yyline, new String(yytext()));
                                }

  {WhiteSpace}                  { /* Así ignora los espacios en blanco */ }
  {Comentario}                  { /* Así ignora los comentarios */ }
}

[^] { Error = String.format("Caracter no permitido: <" + yytext() + "> en la linea " + yyline); throw new Error(Error); }
