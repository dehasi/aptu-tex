#!/usr/bin/python

class abstractmatrix(object):
	def __init__(self, rows, cols):
		self._rows = rows
		self._cols = cols
		self._matrix = [[0 for c in xrange(cols)] for r in xrange(rows)]

	def rows(self):
		return self._rows

	def cols(self):
		return self._cols

	def getitem(self, r, c):
		return self._matrix[r][c]

	def setitem(self, r, c, item):
		self._matrix[r][c]=item

	def __mulmm(self, matrix):
		if not (self.cols() == matrix.rows()):
			raise NotImplementedError('undefined behavior for this matrix size')
		res = abstractmatrix(self.rows(), matrix.cols())
		for r in xrange(res.rows()):
			for c in xrange(res.cols()):
				data = 0
				for i in xrange(self.cols()):
					data = data + self.getitem(r, i)*matrix.getitem(i, c)
				res.setitem(r, c, data)
		return res

	def __mulmk(self, k):
		res = abstractmatrix(self.rows(), self.cols())
		for r in xrange(res.rows()):
			for c in xrange(res.cols()):
				res.setitem(r, c, self.getitem(r, c)*k)
		return res

	def __mul__(self, right):
		if isinstance(right, abstractmatrix):
			return self.__mulmm(right)
		else:
			return self.__mulmk(right)

	def __mulr__(self, left):
		return self.__mulmk(left)

	def __add__(self, matrix):
		if not (self.rows() == matrix.rows()) or not (self.cols() == matrix.cols()):
			raise NotImplementedError('undefined behavior for this matrix size')
		res = abstractmatrix(self.rows(), self.cols())
		for r in xrange(res.rows()):
			for c in xrange(res.cols()):
				res.setitem(r, c, self.getitem(r, c) + matrix.getitem(r, c))
		return res

	def __maxlen(self):
		maxln = 0
		for r in xrange(self.rows()):
			for c in xrange(self.cols()):
				if len(str(self.getitem(r, c))) > maxln:
					maxln = len(str(self.getitem(r, c)))
		return maxln

	def __str__(self):
		res = ""
		ln = self.__maxlen() + 1;
		for r in xrange(self.rows()):
			for c in xrange(self.cols()):
				res = res + str(self.getitem(r, c)).ljust(ln)
			res = res + "\n"
		return res

class squarematrix(abstractmatrix):
	def __init__(self, size):
		self._size = size
		super(squarematrix, self).__init__(size, size)

	def size(self):
		return self._size;

class vector(abstractmatrix):
	def __init__(self, size):
		self._size = size
		super(vector, self).__init__(size, 1)

	def size(self):
		return self._size

	def setitem(self, index, data):
		super(vector, self).setitem(index, 0, data)

identity = squarematrix(3)
for i in xrange(identity.size()):
	identity.setitem(i, i, 1);

print identity

identity2 = identity*2
print identity2

identity3 = identity2*identity2
print identity3

identity4 = identity3 + identity2
print identity4

vector1 = vector(3);
for i in xrange(3):
	vector1.setitem(i, i)

result = identity4*vector1
print result
