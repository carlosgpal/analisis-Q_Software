from qiskit import QuantumCircuit

circuito = QuantumCircuit(4, 4)
circuito.t(0)
circuito.tdg(1)
circuito.s(2)
circuito.sdg(3)
circuito.measure([0, 2, 3, 1], [0, 1, 2, 3])