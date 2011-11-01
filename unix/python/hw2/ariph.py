#!/usr/bin/python

base = 1000

def longsum (num1, num2):
	result = []; take = 0
	maxlen = len(num1)
	if len(num2) > maxlen:
		maxlen = len(num2)
	for i in range(maxlen + 1):
		val1 = 0; val2 = 0
		if i < len(num1):
			val1 = num1[i]
		if i < len(num2):
			val2 = num2[i]
		val = val1 + val2 + take
		if val != 0:
			result.append(val % base)
			if val < base:
				take = 0
			else:
				take = 1
	return result

v1 = [999, 999]
v2 = [999, 999]
v3 = longsum(v1,v2)
print v3
