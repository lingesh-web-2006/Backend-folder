## GitHub Copilot Chat

- Extension: 0.37.1 (prod)
- VS Code: 1.109.0 (bdd88df003631aaa0bcbe057cb0a940b80a476fa)
- OS: win32 10.0.26200 x64
- GitHub Account: lingesh-web-2006

## Network

User Settings:
```json
  "http.systemCertificatesNode": false,
  "github.copilot.advanced.debug.useElectronFetcher": true,
  "github.copilot.advanced.debug.useNodeFetcher": false,
  "github.copilot.advanced.debug.useNodeFetchFetcher": true
```

Connecting to https://api.github.com:
- DNS ipv4 Lookup: timed out after 10 seconds
- DNS ipv6 Lookup: Error (97 ms): getaddrinfo ENOTFOUND api.github.com
- Proxy URL: None (0 ms)
- Electron fetch (configured): HTTP 200 (2172 ms)
- Node.js https: HTTP 200 (522 ms)
- Node.js fetch: HTTP 200 (379 ms)

Connecting to https://api.githubcopilot.com/_ping:
- DNS ipv4 Lookup: 140.82.113.21 (31 ms)
- DNS ipv6 Lookup: Error (18 ms): getaddrinfo ENOTFOUND api.githubcopilot.com
- Proxy URL: None (2 ms)
- Electron fetch (configured): HTTP 200 (1360 ms)
- Node.js https: 