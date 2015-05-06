/* Generated By:JavaCC: Do not edit this line. Yaka.java */
public class Yaka implements YakaConstants {
  public static Declaration declaration;
  public static TabIdent tabIdent;
  public static Expression expression;
  public static YVM yvm;
  public static final String ASMfilename = "org.asm";
  //public static final String YVMfilename = "code.jpeg";
  public static String YVMfilename;

  public static void main(String args[]) {
    Yaka analyseur;

    yvm = new YakaToAsm();
    tabIdent = new TabIdent();
    expression = new Expression();
    declaration = new Declaration("");

    java.io.InputStream input;

    if (args.length==1) {
      System.out.print(args[args.length-1] + ": ");
      try {
        input = new java.io.FileInputStream(args[args.length-1]);
      } catch (java.io.FileNotFoundException e) {
        System.out.println("Fichier introuvable.");
        return;
      }
    } else if (args.length==0) {
      System.out.println("Lecture sur l'entree standard...");
      input = System.in;
    } else {
      System.out.println("Usage: java Yaka[fichier]");
      return;
    }
    try {
      analyseur = new Yaka(input);
      analyseur.prog();
      System.out.println("Analyse syntaxique r\u00e9ussie !");
    } catch (ParseException e) {
      String msg = e.getMessage();
      msg = msg.substring(0,msg.indexOf("\n"));
      System.out.println("Erreur de syntaxe : "+msg);
    }
  }

/**************************************/
/********debut de la grammaire ********/
/**************************************/
  static final public void prog() throws ParseException {
    jj_consume_token(PROGRAMME);
    jj_consume_token(ident);
                         YVMfilename = YakaTokenManager.identLu; yvm.entete();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BOOLEEN:
      case ENTIER:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      declFonction();
    }
    jj_consume_token(PRINCIPAL);
    bloc();
    jj_consume_token(FPRINCIPAL);
    jj_consume_token(FPROGRAMME);
                 yvm.queue();
  }

  static final public void bloc() throws ParseException {
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CONST:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      declConst();
    }
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_3;
      }
      declVar();
    }
  yvm.ouvBloc(tabIdent.getNbVariable());yvm.ouvrePrinc();
    suiteInstr();
  }

  static final public void declConst() throws ParseException {
    jj_consume_token(CONST);
    defConst();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 40:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_4;
      }
      jj_consume_token(40);
      defConst();
    }
    jj_consume_token(41);
  }

  static final public void defConst() throws ParseException {
    jj_consume_token(ident);
           declaration = new Declaration(YakaTokenManager.identLu);
    jj_consume_token(42);
    valConst();
  }

  static final public void valConst() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case entier:
      jj_consume_token(entier);
             declaration.setIdent( Type.ENTIER, YakaTokenManager.entierLu);
      break;
    case ident:
      jj_consume_token(ident);
             declaration.setIdent(tabIdent.getType(YakaTokenManager.identLu), tabIdent.getValue(YakaTokenManager.identLu ));
      break;
    case VRAI:
      jj_consume_token(VRAI);
             declaration.setIdent(Type.BOOLEEN, -1);
      break;
    case FAUX:
      jj_consume_token(FAUX);
             declaration.setIdent(Type.BOOLEEN, 0);
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void declVar() throws ParseException {
    jj_consume_token(VAR);
    type();
    jj_consume_token(ident);
           declaration.setIdent(YakaTokenManager.identLu);yvm.alloc();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 40:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_5;
      }
      jj_consume_token(40);
      jj_consume_token(ident);
               declaration.setIdent(YakaTokenManager.identLu);yvm.alloc();
    }
    jj_consume_token(41);
  }

  static final public void type() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ENTIER:
      jj_consume_token(ENTIER);
             declaration.setType(Type.ENTIER);
      break;
    case BOOLEEN:
      jj_consume_token(BOOLEEN);
              declaration.setType(Type.BOOLEEN);
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

/*
 * Syntaxe des instructions.
 */
  static final public void suiteInstr() throws ParseException {
    instruction();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 41:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_6;
      }
      jj_consume_token(41);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SI:
      case RETOURNE:
      case TANTQUE:
      case ALALIGNE:
      case ident:
      case 43:
      case 45:
        instruction();
        break;
      default:
        jj_la1[8] = jj_gen;
        ;
      }
    }
  }

  static final public void instruction() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ident:
      affectation();
      break;
    case 43:
      lecture();
      break;
    case ALALIGNE:
    case 45:
      ecriture();
      break;
    case TANTQUE:
      iteration();
      break;
    case SI:
      conditionnelle();
      break;
    case RETOURNE:
      retourne();
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void conditionnelle() throws ParseException {
    jj_consume_token(SI);
    expression();
                           expression.isBooleanExpression();
    jj_consume_token(ALORS);
                 yvm.iffauxCond();
    suiteInstr();
         yvm.goTo(); yvm.sinon();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SINON:
      jj_consume_token(SINON);
      suiteInstr();
      break;
    default:
      jj_la1[10] = jj_gen;
      ;
    }
    jj_consume_token(FSI);
               yvm.fsi();
  }

  static final public void iteration() throws ParseException {
    jj_consume_token(TANTQUE);
                   yvm.tantQue();
    expression();
                       expression.isBooleanExpression();
    jj_consume_token(FAIRE);
                 yvm.faire();
    suiteInstr();
    jj_consume_token(FAIT);
                yvm.fait();
  }

  static final public void affectation() throws ParseException {
    jj_consume_token(ident);
                 expression.setIdentAffected(YakaTokenManager.identLu);
    jj_consume_token(42);
    expression();
                          expression.controlTypeAffectation();
  }

  static final public void lecture() throws ParseException {
    jj_consume_token(43);
    jj_consume_token(ident);
         yvm.lireEnt(tabIdent.findIdent(YakaTokenManager.identLu).getValue());
    jj_consume_token(44);
  }

  static final public void ecriture() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 45:
      jj_consume_token(45);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VRAI:
      case FAUX:
      case NON:
      case entier:
      case ident:
      case 46:
      case 53:
        expression();
                                yvm.ecrireEnt();
        break;
      case chaine:
        jj_consume_token(chaine);
                                                             yvm.ecrireChaine(YakaTokenManager.chaineLue);
        break;
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(44);
      break;
    case ALALIGNE:
      jj_consume_token(ALALIGNE);
                      yvm.aLaLigne();
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

/*
 * Expression .
 */
  static final public void expression() throws ParseException {
    simpleExpr();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 42:
    case 47:
    case 48:
    case 49:
    case 50:
    case 51:
      opRel();
      simpleExpr();
                expression.controlType();
      break;
    default:
      jj_la1[13] = jj_gen;
      ;
    }
  }

  static final public void simpleExpr() throws ParseException {
    terme();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OU:
      case 52:
      case 53:
        ;
        break;
      default:
        jj_la1[14] = jj_gen;
        break label_7;
      }
      opAdd();
      terme();
                 expression.controlType();
    }
  }

  static final public void terme() throws ParseException {
    facteur();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ET:
      case 54:
      case 55:
        ;
        break;
      default:
        jj_la1[15] = jj_gen;
        break label_8;
      }
      opMul();
      facteur();
                 expression.controlType();
    }
  }

  static final public void facteur() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VRAI:
    case FAUX:
    case entier:
    case ident:
    case 46:
      primaire();
      break;
    case NON:
    case 53:
      opNeg();
      primaire();
                         expression.controlType();
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void primaire() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VRAI:
    case FAUX:
    case entier:
    case ident:
      valeur();
      break;
    case 46:
      jj_consume_token(46);
      expression();
      jj_consume_token(44);
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void valeur() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case entier:
      jj_consume_token(entier);
                 expression.pushOperande(Type.ENTIER);
                 yvm.iconst(YakaTokenManager.entierLu);
      break;
    case ident:
      jj_consume_token(ident);
                 expression.pushOperande(tabIdent.getType(YakaTokenManager.identLu));
                 expression.accedeIdent(YakaTokenManager.identLu);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 46:
        argumentsFonction();
        break;
      default:
        jj_la1[18] = jj_gen;
        ;
      }
                           ;
      break;
    case VRAI:
      jj_consume_token(VRAI);
                 expression.pushOperande(Type.BOOLEEN);
                 yvm.iconst(-1);
      break;
    case FAUX:
      jj_consume_token(FAUX);
                 expression.pushOperande(Type.BOOLEEN);
                 yvm.iconst(0);
      break;
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opRel() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 42:
      jj_consume_token(42);
                 expression.pushOperator(Operateur.EG);
      break;
    case 47:
      jj_consume_token(47);
                 expression.pushOperator(Operateur.DIFF);
      break;
    case 48:
      jj_consume_token(48);
                 expression.pushOperator(Operateur.INF);
      break;
    case 49:
      jj_consume_token(49);
                 expression.pushOperator(Operateur.INFEG);
      break;
    case 50:
      jj_consume_token(50);
                 expression.pushOperator(Operateur.SUP);
      break;
    case 51:
      jj_consume_token(51);
                 expression.pushOperator(Operateur.SUPEG);
      break;
    default:
      jj_la1[20] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opAdd() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 52:
      jj_consume_token(52);
                 expression.pushOperator(Operateur.PLUS);
      break;
    case 53:
      jj_consume_token(53);
                 expression.pushOperator(Operateur.MOINS);
      break;
    case OU:
      jj_consume_token(OU);
                 expression.pushOperator(Operateur.OU);
      break;
    default:
      jj_la1[21] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opMul() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 54:
      jj_consume_token(54);
                 expression.pushOperator(Operateur.MULT);
      break;
    case 55:
      jj_consume_token(55);
                 expression.pushOperator(Operateur.DIV);
      break;
    case ET:
      jj_consume_token(ET);
                 expression.pushOperator(Operateur.ET);
      break;
    default:
      jj_la1[22] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opNeg() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 53:
      jj_consume_token(53);
                 expression.pushOperator(Operateur.NEG);
      break;
    case NON:
      jj_consume_token(NON);
                 expression.pushOperator(Operateur.NON);
      break;
    default:
      jj_la1[23] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void declFonction() throws ParseException {
    type();
         Type typeDeRetour = declaration.getType();
    jj_consume_token(FONCTION);
    jj_consume_token(ident);
                 declaration.fonctionEnCours = new Function(YakaTokenManager.identLu, typeDeRetour);
    paramForms();
    bloc();
    jj_consume_token(FFONCTION);
  }

  static final public void paramForms() throws ParseException {
    jj_consume_token(46);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEEN:
    case ENTIER:
      paramForm();
      label_9:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 40:
          ;
          break;
        default:
          jj_la1[24] = jj_gen;
          break label_9;
        }
        jj_consume_token(40);
        paramForm();
      }
      break;
    default:
      jj_la1[25] = jj_gen;
      ;
    }
    jj_consume_token(44);
                                                    declaration.fonctionEnCours.calculerOffsetsDesParametres();
  }

  static final public void paramForm() throws ParseException {
    type();
                Type typeDuParametre = declaration.getType();
    jj_consume_token(ident);
                                declaration.fonctionEnCours.addParametre(new Param(YakaTokenManager.identLu, typeDuParametre));
  }

  static final public void argumentsFonction() throws ParseException {
    jj_consume_token(46);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VRAI:
    case FAUX:
    case NON:
    case entier:
    case ident:
    case 46:
    case 53:
      expression();
      label_10:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 40:
          ;
          break;
        default:
          jj_la1[26] = jj_gen;
          break label_10;
        }
        jj_consume_token(40);
        expression();
      }
      break;
    default:
      jj_la1[27] = jj_gen;
      ;
    }
    jj_consume_token(44);
  }

  static final public void retourne() throws ParseException {
    jj_consume_token(RETOURNE);
    expression();
  }

  static private boolean jj_initialized_once = false;
  static public YakaTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  static public Token token, jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[28];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_0();
      jj_la1_1();
   }
   private static void jj_la1_0() {
      jj_la1_0 = new int[] {0x8100,0x80000,0x200,0x0,0x120000,0x0,0x8100,0x0,0x52000,0x52000,0x800,0x1120000,0x0,0x0,0x400000,0x800000,0x1120000,0x120000,0x0,0x120000,0x0,0x400000,0x800000,0x1000000,0x0,0x8100,0x0,0x1120000,};
   }
   private static void jj_la1_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x100,0x50,0x100,0x0,0x200,0x2844,0x2844,0x0,0x2040d0,0x2004,0xf8400,0x300000,0xc00000,0x204050,0x4050,0x4000,0x50,0xf8400,0x300000,0xc00000,0x200000,0x100,0x0,0x100,0x204050,};
   }

  public Yaka(java.io.InputStream stream) {
     this(stream, null);
  }
  public Yaka(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  You must");
      System.out.println("       either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new YakaTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
  }

  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
  }

  public Yaka(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  You must");
      System.out.println("       either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new YakaTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
  }

  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
  }

  public Yaka(YakaTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  You must");
      System.out.println("       either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
  }

  public void ReInit(YakaTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
  }

  static final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static final private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.Vector jj_expentries = new java.util.Vector();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  static public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[56];
    for (int i = 0; i < 56; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 28; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 56; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  static final public void enable_tracing() {
  }

  static final public void disable_tracing() {
  }

}
