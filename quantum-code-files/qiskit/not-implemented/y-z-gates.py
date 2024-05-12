from qiskit import QuantumCircuit

circuito = QuantumCircuit(4, 4)
circuito.y(1)
circuito.z(2)
circuito.measure([0, 2, 3, 1], [0, 1, 2, 3])