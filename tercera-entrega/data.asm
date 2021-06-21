.MODEL	LARGE
.386
.STACK 200h

	_@b	db	?
	_@a	db	?
	_@d	db	?
	_@c	db	?
	_@e	db	?
	_@h	db	?
	_@g	db	?
	_@j	db	?
	_@i	db	?
	_@p	db	?
	_@o	db	?
	_a_es_menor_a_10	db	"a es menor a 10",'$'
	_@aux3	db	?
	_@aux4	db	?
	_0.342	dd	0.342
	_@aux5	db	?
	_@aux6	db	?
	_Fin_de_programa	db	"Fin de programa",'$'
	_@aux7	db	?
	_Contadorx5	db	"Contadorx5",'$'
	_0	dd	0
	_1	dd	1
	_2	dd	2
	_5	dd	5
	_@aux1	db	?
	_11	dd	11
	_10	dd	10
	_@aux2	db	?
.CODE

START:
	mov ax, @DATA
	mov ds, ax
	mov es, ax
	fld h
	fld i
	fcomp
	fstsw ax
	sahf
	jne IF_UNARIO_1_ELSE
	fld a
	fild _1
	fadd
	fstp _@aux1
	fstp a
	jmp IF_UNARIO_1_END
IF_UNARIO_1_ELSE:
	fld b
	fstp a
IF_UNARIO_1_END:
	fild _0
	fstp c
	fild _2
	fstp a
WHILE_1_START:
	fild _10
	fld a
	fcomp
	fstsw ax
	sahf
	jnb WHILE_1_END
	mov dx, OFFSET _a_es_menor_a_10
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	fld a
	fild _1
	fadd
	fstp _@aux2
	fstp a
	jmp WHILE_1_START
WHILE_1_END:
	fld c
	fild _1
	fadd
	fstp _@aux3
	fstp c
WHILE_2_START:
	fild _5
	fld c
	fcomp
	fstsw ax
	sahf
	jnae WHILE_2_END
	fld c
	fild _1
	fadd
	fstp _@aux4
	fstp c
	mov dx, OFFSET _Contadorx5
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	jmp WHILE_2_START
WHILE_2_END:
	fld c
	fld c
	fmul
	fstp _@aux6
	fld c
	fld _0.342
	fdiv
	fstp _@aux5
	fld _@aux5
	fld _@aux6
	fadd
	fstp _@aux7
	fstp a
	fild _11
	fstp a
	mov dx, OFFSET _Fin_de_programa
	mov ah, 9
	int 21h
	mov ah, 2
	mov dl, 13
	int 21h
	mov dl, 10
	int 21h
	;mov ah, 1
	;int 21h
	mov ax, 4C00h
	int 21h
END START

