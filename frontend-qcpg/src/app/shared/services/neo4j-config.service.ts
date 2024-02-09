import { Injectable } from '@angular/core';

export interface Neo4jConfig {
    host: string;
    port: number;
    username: string;
    password: string;
}

@Injectable({
    providedIn: 'root'
})
export class Neo4jConfigService {
    private config: Neo4jConfig | null = null;

    setConfig(config: Neo4jConfig) {
        this.config = config;
    }

    getConfig(): Neo4jConfig | null {
        return this.config;
    }
}