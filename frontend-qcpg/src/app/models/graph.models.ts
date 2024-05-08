import { SimulationNodeDatum, SimulationLinkDatum } from 'd3';

export interface Node extends SimulationNodeDatum {
    id: string;
    name: string;
    file: string;
    code: string;
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
    mappingBits?: Graph;
    mappingGates?: Graph;
    mappingMeasures?: Graph;
}

export interface File {
    id: number;
    name: string;
    content: string;
    open?: boolean;
}