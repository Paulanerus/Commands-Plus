package pw.paul.command.exception;

import pw.paul.command.model.Command;
import pw.paul.command.model.execution.Execute;

/**
 * Exception if the {@link Command} has no method annotated with {@link Execute}.
 *
 * @author Paul
 */
public final class MissingExecuteDefinitionException extends RuntimeException {
}
