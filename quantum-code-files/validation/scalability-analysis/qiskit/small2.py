from qiskit import QuantumCircuit

circuito = QuantumCircuit(2, 2)
circuito.x(0)
circuito.h(1)
circuito.x(1)
circuito.h(0)
circuito.cx(0, 1)
circuito.x(0)
circuito.h(1)
circuito.cx(0, 1)
circuito.h(0)
circuito.measure([0, 1], [0, 1])
