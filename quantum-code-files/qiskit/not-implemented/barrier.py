from qiskit import QuantumCircuit

circuito = QuantumCircuit(5, 5)
circuito.x(1)
circuito.x(4)
circuito.barrier()
circuito.cx(0, 2)
circuito.cx(1, 2)
circuito.cx(3, 2)
circuito.cx(4, 2)
circuito.barrier()
circuito.measure(range(5), range(5))