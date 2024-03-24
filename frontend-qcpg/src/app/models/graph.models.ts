import { SimulationNodeDatum, SimulationLinkDatum } from 'd3';

export interface Node extends SimulationNodeDatum {
    id: string;
    name: string;
    labels: string[];
}

export interface Link extends SimulationLinkDatum<Node> {
    id: string;
    source: string | Node;
    target: string | Node;
    type: string;
}

export interface Graph {
    nodes: Node[];
    edges: Link[];
    type: string;
}

export interface GraphCache {
    entireGraph?: Graph;
    ast?: Graph;
    cfg?: Graph;
    pdg?: Graph;
}