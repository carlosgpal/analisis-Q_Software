from qiskit import QuantumCircuit

circuito = QuantumCircuit(15, 15)
circuito.h(0)
circuito.x(1)
circuito.h(2)
circuito.cx(3, 4)
circuito.h(5)
circuito.x(6)
circuito.h(7)
circuito.cx(8, 9)
circuito.h(10)
circuito.x(11)
circuito.h(12)
circuito.cx(13, 14)
circuito.h(0)
circuito.x(1)
circuito.h(2)
circuito.cx(3, 4)
circuito.h(5)
circuito.x(1)
circuito.h(2)
circuito.cx(3, 4)
circuito.x(6)
circuito.h(7)
circuito.cx(8, 9)
circuito.h(10)
circuito.cx(3, 4)
circuito.x(6)
circuito.h(7)
circuito.cx(8, 9)
circuito.h(10)
circuito.x(11)
circuito.h(12)
circuito.cx(13, 14)
circuito.h(0)
circuito.x(1)
circuito.h(2)
circuito.cx(3, 4)
circuito.h(5)
circuito.x(6)
circuito.h(7)
circuito.h(5)
circuito.x(6)
circuito.h(7)
circuito.cx(8, 9)
circuito.h(10)
circuito.x(11)
circuito.h(12)
circuito.cx(13, 14)
circuito.h(0)
circuito.x(1)
circuito.h(2)
circuito.cx(3, 4)
circuito.measure(range(15), range(15))
