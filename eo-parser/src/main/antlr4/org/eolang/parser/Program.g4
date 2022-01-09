grammar Program;

tokens { TAB, UNTAB }

program
  :
  license?
  metas?
  objects
  EOF
  ;

license
  :
  (COMMENT EOL)+
  ;

metas
  :
  (META EOL)+
  ;

objects
  :
  (
    (COMMENT EOL)*
    object
    EOL
  )+
  ;

object
  :
  (COMMENT EOL)*
  (
    abstraction
    |
    application
  )
  tail?
  (
    EOL
    method
    (SPACE htail)?
    suffix?
    tail?
  )*
  ;

abstraction
  :
     LSQ (attribute (SPACE attribute)*)? RSQ
  (
     (suffix (SPACE SLASH (NAME | QUESTION))?)
   | (SPACE application)+
  )?
  ;

attribute
  :
  label
  ;

label
  :
  AT
  |
  NAME
  DOTS?
  ;

tail
  :
  EOL
  TAB
  (object EOL)+
  UNTAB
  ;

suffix
  :
  SPACE
  ARROW
  SPACE
  label
  CONST?
  ;

method
  :
  DOT
  mtd=(
    NAME
    |
    RHO
    |
    AT
    |
    VERTEX
  )
  ;

application
  :
    head (SPACE htail)*
  | application (method | has | suffix) (SPACE htail)*
  | LB application RB (SPACE htail)*
  ;

htail
  :
    head
  | LB application RB
  | application (method | has | suffix)
  | abstraction
  ;

head
  :
  DOTS?
  (
    ROOT
    |
    AT
    |
    RHO
    |
    XI
    |
    SIGMA
    |
    STAR
    |
    NAME
    COPY?
    |
    NAME
    DOT
    |
    data
  )
  ;

has
  :
  COLON
  NAME
  ;

data
  :
  BYTES
  |
  BOOL
  |
  TEXT
  |
  STRING
  |
  INT
  |
  FLOAT
  |
  HEX
  |
  CHAR
  |
  REGEX
  ;

COMMENT: HASH ~[\r\n]*;
META: PLUS NAME (SPACE ~[\r\n]+)?;

REGEX: SLASH ~[\r\n]+ SLASH [a-z]*;

ROOT: 'Q';
STAR: '*';
DOTS: '...';
CONST: '!';
SLASH: '/';
COLON: ':';
COPY: '\'';
ARROW: '>';
VERTEX: '<';
SIGMA: '&';
XI: '$';
PLUS: '+';
MINUS: '-';
QUESTION: '?';
SPACE: ' ';
DOT: '.';
LSQ: '[';
RSQ: ']';
LB: '(';
RB: ')';
AT: '@';
RHO: '^';
HASH: '#';

fragment INDENT:
    SPACE SPACE
    ;
fragment LINEBREAK:
    ('\n' | '\r\n')
    ;
EOL
  :
  LINEBREAK
  LINEBREAK?
  INDENT*
  ;

fragment BYTE: [0-9A-F][0-9A-F];
fragment EMPTY_BYTES : MINUS MINUS;
fragment LINE_BYTES : BYTE (MINUS BYTE)+;

BYTES:
       EMPTY_BYTES
    |  BYTE MINUS
    |  LINE_BYTES (MINUS EOL LINE_BYTES)*;

BOOL: 'TRUE' | 'FALSE';
CHAR:  '\'' (~['\\\r\n] | ESCAPE_SEQUENCE) '\'';
STRING: '"' (~["\\\r\n] | ESCAPE_SEQUENCE)* '"';

fragment ESCAPE_SEQUENCE
    : '\\' [btnfr"'\\]
    | '\\' ([0-3]? [0-7])? [0-7]
    | '\\' 'u'+ BYTE BYTE
    ;
INT: (PLUS | MINUS)? [0-9]+;
FLOAT: (PLUS | MINUS)? [0-9]+ DOT [0-9]+;
HEX: '0x' [0-9a-f]+;

NAME: [a-z][\p{Letter}\p{General_Category=Decimal_Number}_-]*;

fragment TEXT_MARK: '"""';
TEXT:
    TEXT_MARK ('\n' | '\r\n')
    (~[\\] | ESCAPE_SEQUENCE)*?
    TEXT_MARK
    ;
