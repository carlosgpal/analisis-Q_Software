from qiskit import QuantumCircuit

circuito = QuantumCircuit(4, 4)
for i in range(3):
    circuito.h(i)