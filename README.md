# spotify-music
# Patrón A — Sin path variables (Caterpillar):
java@PostMapping
public ResponseEntity<?> createXxx(@Valid @RequestBody CreateXxxResource resource) {
    var command = XxxAssembler.toCommand(resource);
    var entity = commandService.handle(command);
    return ResponseEntity.status(HttpStatus.CREATED).body(XxxAssembler.toResource(entity));
}
# Patrón B — Con path variables (Kohler, Spotify, Atlassian):
java@PostMapping
public ResponseEntity<?> createXxx(
        @PathVariable String/Long id,
        @Valid @RequestBody CreateXxxResource resource) {
    var command = XxxAssembler.toCommand(resource, id);
    var entity = commandService.handle(command);
    return ResponseEntity.status(HttpStatus.CREATED).body(XxxAssembler.toResource(entity));
}
