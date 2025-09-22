import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import {BrowserRouter,Routes,Route} from 'react-router-dom'
import Homepage from './components/Homepage.jsx'
import Dashboard from './components/Dashboard.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
  <BrowserRouter>
    <Routes>
      {/* Route for the public homepage */}
      <Route path='/homepage' element={<Homepage/>}/>
      <Route path='/' element={<Homepage/>}/>

      {/* Routes for the dashboard, now directly accessible */}
      <Route path='/dashboard' element={<Dashboard/>}/>
      <Route path='/recipe-frontend' element={<Dashboard/>}/>
    </Routes>
  </BrowserRouter>
  </StrictMode>
)