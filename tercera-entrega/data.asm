.MODEL	LARGE
.386
.STACK 200h
.DATA

	_0_8	dd	0.8
	_@aux150	dd	?
	_@aux151	dd	?
	_Contador_While_x5_AND	db	"Contador While x5 AND","$"
	_@aux152	dd	?
	_6544	dd	6544
	_0_4	dd	0.4
	_a_y_b_son_iguales	db	"a y b son iguales","$"
	_@aux117	dd	?
	_@aux118	dd	?
	_100	dd	100
	_@aux119	dd	?
	_500	dd	500
	_@aux115	dd	?
	_@aux116	dd	?
	_@aux120	dd	?
	_@aux121	dd	?
	_@aux122	dd	?
	_@aux123	dd	?
	_OK_4	db	"OK 4","$"
	_OK_3	db	"OK 3","$"
	_0	dd	0
	_OK_2	db	"OK 2","$"
	_h_es_menor_a_k	db	"h es menor a k","$"
	_1	dd	1
	_OK_1	db	"OK 1","$"
	_2	dd	2
	_b_es_menor	db	"b es menor","$"
	_5	dd	5
	_@aux128	dd	?
	_@aux129	dd	?
	_9	dd	9
	_50	dd	50
	_@aux124	dd	?
	_@aux125	dd	?
	_@aux126	dd	?
	_10	dd	10
	_@aux127	dd	?
	_@aux131	dd	?
	_@aux132	dd	?
	_12	dd	12
	_15	dd	15
	_@aux133	dd	?
	_@aux134	dd	?
	_h_es_mayor_a_k	db	"h es mayor a k","$"
	_@aux130	dd	?
	_FLOATS_IGUALES	db	"FLOATS IGUALES","$"
	_Condicion_doble	db	"Condicion doble","$"
	_d_es_igual_a_500	db	"d es igual a 500","$"
	_ContadorRegresivox5	db	"ContadorRegresivox5","$"
	_@aux139	dd	?
	_20	dd	20
	_@aux135	dd	?
	_@aux136	dd	?
	_@aux137	dd	?
	_@aux138	dd	?
	_@aux142	dd	?
	a	dd	?
	_@aux143	dd	?
	b	dd	?
	_@aux144	dd	?
	c	dd	?
	_@aux145	dd	?
	d	dd	?
	e	dd	?
	_Contadorx5	db	"Contadorx5","$"
	_@aux140	dd	?
	_@aux141	dd	?
	h	dd	?
	k	dd	?
	_d_es_igual_a_100	db	"d es igual a 100","$"
	_Contador_While_x5_OR	db	"Contador While x5 OR","$"
	_@aux146	dd	?
	_b_es_mayor	db	"b es mayor","$"
	_@aux147	dd	?
	_30_2	dd	30.2
	_@aux148	dd	?
	_@aux149	dd	?
.CODE

START:
	mov ax, @DATA
	mov ds, ax
	mov es, ax
	fild _6544
	fstp b
	fild _6544
	fstp a
	fld a
	fld b
	fcomp
	fstsw ax
	sahf
	jne IF_46_END
	mov dx, OFFSET _a_y_b_son_iguales
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp IF_46_END
IF_46_END:
	fld b
	fild _1
	fadd
	fstp _@aux115
	fld _@aux115
	fstp b
	fld a
	fld b
	fcomp
	fstsw ax
	sahf
	jna IF_47_END
	mov dx, OFFSET _b_es_mayor
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp IF_47_END
IF_47_END:
	fld a
	fild _2
	fadd
	fstp _@aux116
	fld _@aux116
	fstp a
	fld a
	fld b
	fcomp
	fstsw ax
	sahf
	jnb IF_48_END
	mov dx, OFFSET _b_es_menor
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp IF_48_END
IF_48_END:
	fild _1
	fstp c
WHILE_13_START:
	fild _5
	fld c
	fcomp
	fstsw ax
	sahf
	jnbe WHILE_13_END
	fld c
	fild _1
	fadd
	fstp _@aux117
	fld _@aux117
	fstp c
	mov dx, OFFSET _Contadorx5
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp WHILE_13_START
WHILE_13_END:
	fild _5
	fstp c
WHILE_14_START:
	fild _0
	fld c
	fcomp
	fstsw ax
	sahf
	jna WHILE_14_END
	fld c
	fild _1
	fsub
	fstp _@aux118
	fld _@aux118
	fstp c
	mov dx, OFFSET _ContadorRegresivox5
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp WHILE_14_START
WHILE_14_END:
	fild _1
	fstp d
	fild _1
	fstp e
	fild _9
	fstp a
	fild _10
	fstp b
	fld e
	fld d
	fcomp
	fstsw ax
	sahf
	jne IF_49_END
	fld b
	fld a
	fcomp
	fstsw ax
	sahf
	jnb IF_49_END
	mov dx, OFFSET _Condicion_doble
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp IF_49_END
IF_49_END:
	fild _1
	fstp c
WHILE_15_START:
	fld e
	fld d
	fcomp
	fstsw ax
	sahf
	jne WHILE_15_END
	fild _5
	fld c
	fcomp
	fstsw ax
	sahf
	jnbe WHILE_15_END
	fld c
	fild _1
	fadd
	fstp _@aux119
	fld _@aux119
	fstp c
	mov dx, OFFSET _Contador_While_x5_AND
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp WHILE_15_START
WHILE_15_END:
	fild _1
	fstp c
WHILE_16_START:
	fld e
	fld d
	fcomp
	fstsw ax
	sahf
	je OR_12
	fild _5
	fld c
	fcomp
	fstsw ax
	sahf
	jnbe WHILE_16_END
OR_12:
	fld c
	fild _1
	fadd
	fstp _@aux120
	fld _@aux120
	fstp c
	mov dx, OFFSET _Contador_While_x5_OR
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	fild _5
	fld c
	fcomp
	fstsw ax
	sahf
	jne IF_50_END
	fild _0
	fstp d
	jmp IF_50_END
IF_50_END:
	jmp WHILE_16_START
WHILE_16_END:
	fild _10
	fild _2
	fdiv
	fstp _@aux121
	fld _@aux121
	fstp c
	fild _5
	fld c
	fcomp
	fstsw ax
	sahf
	jne IF_51_END
	mov dx, OFFSET _OK_1
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp IF_51_END
IF_51_END:
	fild _5
	fild _2
	fmul
	fstp _@aux124
	fild _10
	fild _2
	fdiv
	fstp _@aux122
	fld _@aux122
	fild _10
	fadd
	fstp _@aux123
	fld _@aux123
	fld _@aux124
	fadd
	fstp _@aux125
	fld _@aux125
	fild _5
	fsub
	fstp _@aux126
	fld _@aux126
	fstp c
	fild _20
	fld c
	fcomp
	fstsw ax
	sahf
	jne IF_52_END
	mov dx, OFFSET _OK_2
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp IF_52_END
IF_52_END:
	fild _5
	fild _2
	fmul
	fstp _@aux129
	fild _10
	fild _2
	fdiv
	fstp _@aux127
	fld _@aux127
	fild _10
	fadd
	fstp _@aux128
	fld _@aux128
	fld _@aux129
	fadd
	fstp _@aux130
	fld _@aux130
	fild _5
	fsub
	fstp _@aux131
	fld _@aux131
	fstp c
	fild _15
	fild _2
	fmul
	fstp _@aux132
	fild _50
	fld _@aux132
	fsub
	fstp _@aux133
	fild _5
	fild _2
	fmul
	fstp _@aux136
	fild _10
	fild _2
	fdiv
	fstp _@aux134
	fld _@aux134
	fild _10
	fadd
	fstp _@aux135
	fld _@aux135
	fld _@aux136
	fadd
	fstp _@aux137
	fld _@aux137
	fild _5
	fsub
	fstp _@aux138
	fld _@aux138
	fld _@aux133
	fcomp
	fstsw ax
	sahf
	jne IF_53_END
	mov dx, OFFSET _OK_3
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp IF_53_END
IF_53_END:
	fild _20
	fstp c
	fild _15
	fild _2
	fmul
	fstp _@aux139
	fild _50
	fld _@aux139
	fsub
	fstp _@aux140
	fld _@aux140
	fld c
	fxch
	fcomp
	fstsw ax
	sahf
	jne IF_54_END
	mov dx, OFFSET _OK_4
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp IF_54_END
IF_54_END:
	fild _5
	fild _2
	fmul
	fstp _@aux143
	fild _10
	fild _2
	fdiv
	fstp _@aux141
	fld _@aux141
	fild _10
	fadd
	fstp _@aux142
	fld _@aux142
	fld _@aux143
	fadd
	fstp _@aux144
	fld _@aux144
	fild _5
	fsub
	fstp _@aux145
	fld _@aux145
	fstp c
	fild _20
	fld c
	fcomp
	fstsw ax
	sahf
	jne IF_UNARIO_55_ELSE
	fld c
	fild _1
	fadd
	fstp _@aux146
	fld _@aux146
	fstp d
	jmp IF_UNARIO_55_END
IF_UNARIO_55_ELSE:
	fld c
	fild _1
	fsub
	fstp _@aux147
	fld _@aux147
	fstp d
IF_UNARIO_55_END:
	fild _20
	fstp c
	fild _12
	fild _2
	fdiv
	fstp _@aux149
	fild _15
	fild _1
	fsub
	fstp _@aux148
	fld _@aux148
	fld _@aux149
	fadd
	fstp _@aux150
	fld _@aux150
	fild _20
	fxch
	fcomp
	fstsw ax
	sahf
	jne IF_UNARIO_56_ELSE
	fild _100
	fstp d
	jmp IF_UNARIO_56_END
IF_UNARIO_56_ELSE:
	fild _500
	fstp d
IF_UNARIO_56_END:
	fild _100
	fld d
	fcomp
	fstsw ax
	sahf
	jne IF_ELSE_57_ELSE
	mov dx, OFFSET _d_es_igual_a_100
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp IF_ELSE_57_END
IF_ELSE_57_ELSE:
	mov dx, OFFSET _d_es_igual_a_500
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
IF_ELSE_57_END:
	fld _30_2
	fstp h
	fld _30_2
	fstp k
	fld k
	fld h
	fcomp
	fstsw ax
	sahf
	jne IF_58_END
	mov dx, OFFSET _FLOATS_IGUALES
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp IF_58_END
IF_58_END:
	fld k
	fld _0_4
	fadd
	fstp _@aux151
	fld _@aux151
	fstp k
	fld k
	fld h
	fcomp
	fstsw ax
	sahf
	jnb IF_59_END
	mov dx, OFFSET _h_es_menor_a_k
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp IF_59_END
IF_59_END:
	fld h
	fld _0_8
	fadd
	fstp _@aux152
	fld _@aux152
	fstp h
	fld k
	fld h
	fcomp
	fstsw ax
	sahf
	jna IF_60_END
	mov dx, OFFSET _h_es_mayor_a_k
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp IF_60_END
IF_60_END:
	;mov ah, 1
	;int 21h
	mov ax, 4C00h
	int 21h
END START

