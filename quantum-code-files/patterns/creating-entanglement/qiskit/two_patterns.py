from qiskit import QuantumCircuit

circuito = QuantumCircuit(3, 3)
circuito.x(2)
circuito.h(1)

circuito.h(0)
circuito.x(2)

circuito.cx(0, 1)
circuito.h(1)
circuito.x(0)

circuito.h(1)
circuito.x(0)

circuito.cx(1, 2)

circuito.h(0)
circuito.x(0)
circuito.h(0)

circuito.x(0)
circuito.cx(0, 2)
circuito.measure([0, 2, 1], [0, 1, 2])