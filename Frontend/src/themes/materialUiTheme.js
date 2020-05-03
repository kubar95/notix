import { createMuiTheme } from '@material-ui/core/styles';
import variables from '../styles/abstracts/variables.scss';

export const theme = createMuiTheme({
    palette: {
        primary: {
            main: variables.darkGrey,
        },
        secondary: {
            main: variables.orange,
        }
    },
});