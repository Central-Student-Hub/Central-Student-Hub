import React, { useState } from 'react';
import './Fees.css'; // Ensure this path is correct based on your project structure

interface FeesInfo {
  feeAmount: number;
  deadline: string;
}

// Mock data for the FeesInfo
const mockFeesInfo: FeesInfo = {
  feeAmount: 1500.00, // Example fee amount
  deadline: '2023-08-01T00:00:00Z', // Example deadline date in ISO format
};

const Fees: React.FC = () => {
  // Initialize state with mock data
  const [feesInfo, setFeesInfo] = useState<FeesInfo>(mockFeesInfo);

  // In a real scenario, you would fetch this data from a backend API:
  // useEffect(() => {
  //   async function fetchFees() {
  //     // Fetch fees info from an API
  //   }
  //   fetchFees();
  // }, []);

  return (
    <div className="fees-container">
      <h1 className='exam-title'>Fees Page</h1>
      <div className="fees-info">
        <p>Fee Amount: ${feesInfo.feeAmount.toFixed(2)}</p>
        <p>Deadline: {new Date(feesInfo.deadline).toLocaleDateString()}</p>
      </div>
    </div>
  );
};

export default Fees;
