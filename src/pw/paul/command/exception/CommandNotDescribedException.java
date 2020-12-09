package pw.paul.command.exception;

import pw.paul.command.model.Command;
import pw.paul.command.model.CommandInfo;

/**
 * Exception if the {@link Command} is not annotated right with {@link CommandInfo}.
 *
 * @author Paul
 */
public class CommandNotDescribedException extends RuntimeException {
}
